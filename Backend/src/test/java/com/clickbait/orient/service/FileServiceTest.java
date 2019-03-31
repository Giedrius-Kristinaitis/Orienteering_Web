package com.clickbait.orient.service;

import com.clickbait.orient.TestDataFactory;
import com.clickbait.orient.config.FileConfig;
import com.clickbait.orient.database.EventRepository;
import com.clickbait.orient.model.Event;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {

    @MockBean
    private FileConfig config;

    @MockBean
    private EventRepository events;

    @Autowired
    private FileService service;

    @Before
    public void setup() {
        when(config.getPhotoUploadDir()).thenReturn("./test_photos");

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
            emptyDirectory(dir);
            dir.delete();
        }
    }

    private static void emptyDirectory(File dir) {
        File[] files = dir.listFiles();

        for (File file: files) {
            if (file.isDirectory()) {
                emptyDirectory(file);
                file.delete();
            } else {
                file.delete();
            }
        }
    }

    @Test
    public void testSavePhoto_shouldReturnNullBecauseEventNotFound() {
        // setup
        Optional<Event> event = Optional.ofNullable(null);

        given(events.findById(any(String.class))).willReturn(event);

        // execute
        String downloadURL = service.savePhoto(null, "event_id");

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
        String downloadURL = service.savePhoto(file, "1");

        // assert
        assertNotNull(downloadURL);
        assertEquals(3, actualEvent.getPhotos().size());
    }

    @Test
    public void testGetPhoto_shouldReturnNull() {
        // execute
        Resource resource = service.getPhoto("not existing photo.png");

        // assert
        assertNull(resource);
    }

    @Test
    public void testGetPhoto_shouldReturnResource() {
        // execute
        Resource resource = service.getPhoto("picture.png");

        // assert
        assertNotNull(resource);
    }
}
