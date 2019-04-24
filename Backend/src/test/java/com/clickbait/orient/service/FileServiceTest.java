package com.clickbait.orient.service;

import com.clickbait.orient.TestDataFactory;
import com.clickbait.orient.database.EventRepository;
import com.clickbait.orient.model.Event;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = "file.photo-upload-dir=./test_photos")
public class FileServiceTest {

    @MockBean
    private EventRepository events;

    @Autowired
    private FileService service;

    @Before
    public void setup() {
        File testFile = new File("./test_photos/picture.png");

        try {
            if (!testFile.exists()) {
                testFile.createNewFile();
            }
        } catch (Exception ex) {}
    }

    @AfterClass
    public static void cleanup() {
        File dir = new File("./test_photos");

        if (dir.exists()) {
            deleteDirectory(dir);
        }
    }

    private static void deleteDirectory(File dir) {
        File[] files = dir.listFiles();

        for (File file: files) {
            if (file.isDirectory()) {
                deleteDirectory(file);
            } else {
                file.delete();
            }
        }

        dir.delete();
    }

    @Test
    public void testSavePhoto_shouldReturnNullBecauseEventNotFound() {
        // setup
        Optional<Event> event = Optional.ofNullable(null);

        given(events.findById(any(String.class))).willReturn(event);

        // execute
        String downloadURL = service.savePhoto(null, "event_id", "not important", "not important");

        // assert
        assertNull(downloadURL);
    }

    @Test
    public void testSavePhoto_shouldReturnNullBecauseInvalidTeamId() {
        // setup
        Optional<Event> event = Optional.of(TestDataFactory.getEvent());

        given(events.findById(any(String.class))).willReturn(event);

        // execute
        String downloadURL = service.savePhoto(null, "event_id", "important", "not important");

        // assert
        assertNull(downloadURL);
    }

    @Test
    public void testSavePhoto_shouldReturnNullBecauseInvalidCheckpointId() {
        // setup
        Optional<Event> event = Optional.of(TestDataFactory.getEvent());

        given(events.findById(any(String.class))).willReturn(event);

        // execute
        String downloadURL = service.savePhoto(null, "event_id", "team1", "important");

        // assert
        assertNull(downloadURL);
    }

    @Test
    public void testSavePhoto_shouldAddPhotoToEvent() {
        // setup
        Optional<Event> event = Optional.of(TestDataFactory.getEvent());

        MockMultipartFile file = new MockMultipartFile("file", "picture.png", "image/png", new byte[100]);

        given(events.findById(any(String.class))).willReturn(event);

        Event actualEvent = event.get();

        // execute
        String downloadURL = service.savePhoto(file, "1", "team1", "1");

        // assert
        assertNotNull(downloadURL);
        assertEquals(2, actualEvent.getPhotos().size());
    }

    @Test
    public void testGetPhoto_shouldReturnResource() {
        // execute
        Resource resource = service.getPhoto("picture.png");

        // assert
        assertNotNull(resource);
    }

    @Test
    public void testGetPhoto_shouldReturnNull() {
        // execute
        Resource resource = service.getPhoto("not existing photo.png");

        // assert
        assertNull(resource);
    }
}
