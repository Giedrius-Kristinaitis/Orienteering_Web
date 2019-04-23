package com.clickbait.orient.service;

import com.clickbait.orient.TestDataFactory;
import com.clickbait.orient.database.EventRepository;
import com.clickbait.orient.model.Event;
import com.clickbait.orient.model.Photo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceTest {

    @Autowired
    private EventService service;

    @MockBean
    private EventRepository repository;

    @Test
    public void testGetEventById_shouldReturnNull() {
        // setup
        String id = "id";

        given(repository.findById(id)).willReturn(Optional.ofNullable(null));

        // execute
        Event returned = service.getEventById(id);

        // assert
        assertEquals(null, returned);
    }

    @Test
    public void testGetEventById_shouldReturnEvent() {
        // setup
        Event event = TestDataFactory.getEvent();

        given(repository.findById(event.getId())).willReturn(Optional.of(event));

        // execute
        Event returned = service.getEventById(event.getId());

        // assert
        assertNotNull(returned);
        assertEquals(event.getId(), returned.getId());
        assertEquals(event.getName(), returned.getName());
        assertEquals(event.getDescription(), returned.getDescription());
        assertEquals(event.getCheckpointCount(), returned.getCheckpointCount());
        assertEquals(event.getTeamSize(), returned.getTeamSize());
        assertEquals(event.getCreated().toString(), returned.getCreated().toString());
        assertEquals(event.getStarting().toString(), returned.getStarting().toString());
        assertEquals(event.getStatus(), returned.getStatus());
        assertEquals(event.getEstimatedDistanceMetres(), returned.getEstimatedDistanceMetres());
        assertEquals(event.getEstimatedTimeMillis(), returned.getEstimatedTimeMillis());

        // added cast for to long because two methods are available to be called here
        // (one with params (Object, Object) and one with (long, long)
        // since checkpoint count and team size can be an object or an number the
        // compiler doesn't know which to use
        assertEquals((long) event.getCheckpoints().size(), (long) returned.getCheckpoints().size());
        assertEquals((long) event.getTeams().size(), (long) returned.getTeams().size());
        assertEquals(event.getPhotos().size(), returned.getPhotos().size());
    }

    @Test
    public void testGetAllEvents_shouldReturnNull() {
        // setup
        Page<Event> page = new PageImpl<>(new ArrayList<>(), PageRequest.of(0, 1), 0);

        given(repository.findAll(any(Pageable.class))).willReturn(page);

        // execute
        Page<Event> returned = service.getAllEvents(0, 1);

        // assert
        assertNull(returned);
    }

    @Test
    public void testGetAllEvents_shouldReturnPageWith1Element() {
        // setup
        Page<Event> events = TestDataFactory.getEventPage(1);

        given(repository.findAll(any(Pageable.class))).willReturn(events);

        // execute
        Page<Event> page = service.getAllEvents(0, 1);

        // assert
        assertNotNull(page);

        assertNotNull(page.getContent());
        assertEquals(1, page.getContent().size());
        assertNotNull(page.getContent().get(0));
        assertEquals(events.getContent().get(0).getId(), page.getContent().get(0).getId());
        assertEquals(events.getContent().get(0).getName(), page.getContent().get(0).getName());
        assertEquals(events.getContent().get(0).getDescription(), page.getContent().get(0).getDescription());
        assertEquals(events.getContent().get(0).getCheckpointCount(), page.getContent().get(0).getCheckpointCount());
        assertEquals(events.getContent().get(0).getTeamSize(), page.getContent().get(0).getTeamSize());
        assertEquals(events.getContent().get(0).getCreated().toString(), page.getContent().get(0).getCreated().toString());
        assertEquals(events.getContent().get(0).getStarting().toString(), page.getContent().get(0).getStarting().toString());
        assertEquals(events.getContent().get(0).getStatus(), page.getContent().get(0).getStatus());
        assertEquals(events.getContent().get(0).getEstimatedDistanceMetres(), page.getContent().get(0).getEstimatedDistanceMetres());
        assertEquals(events.getContent().get(0).getEstimatedTimeMillis(), page.getContent().get(0).getEstimatedTimeMillis());

        // added cast for to long because two methods are available to be called here
        // (one with params (Object, Object) and one with (long, long)
        // since checkpoint count and team size can be an object or an number the
        // compiler doesn't know which to use
        assertEquals((long) events.getContent().get(0).getCheckpoints().size(), (long) page.getContent().get(0).getCheckpoints().size());
        assertEquals((long) events.getContent().get(0).getTeams().size(), (long) page.getContent().get(0).getTeams().size());
        assertEquals(events.getContent().get(0).getPhotos().size(), page.getContent().get(0).getPhotos().size());
    }

    @Test
    public void testGetAllEvents_shouldReturnPageWith5Elements() {
        // setup
        Page<Event> events = TestDataFactory.getEventPage(5);

        given(repository.findAll(any(Pageable.class))).willReturn(events);

        // execute
        Page<Event> page = service.getAllEvents(0, 5);

        // assert
        assertNotNull(page);

        assertNotNull(page.getContent());
        assertEquals(5, page.getContent().size());
    }
  
    @Test
    public void testSaveEvent_shouldReturnNull() {
        // execute
        Event saved = service.saveEvent(null);

        // assert
        assertNull(saved);
    }

    @Test
    public void testSaveEvent_shouldAddNewEvent() {
        // setup
        Event event = TestDataFactory.getEvent();
        event.setId("generated_id");

        given(repository.save(any(Event.class))).willReturn(event);

        Event passedToService = TestDataFactory.getEvent();
        passedToService.setId(null);

        // execute
        Event returned = service.saveEvent(passedToService);

        // assert
        assertNotNull(returned);
        assertEquals(event.getId(), returned.getId());
    }

    @Test
    public void testSaveEvent_shouldUpdateEvent() {
        // setup
        Event event = TestDataFactory.getEvent();
        event.setName("New name");

        given(repository.save(any(Event.class))).willReturn(event);

        // execute
        Event returned = service.saveEvent(event);

        // assert
        assertNotNull(returned);
        assertEquals(event.getId(), returned.getId());
        assertEquals(event.getName(), returned.getName());
    }

    @Test
    public void testGetEventTeamPhotos_shouldReturnNullBecauseEventNotFound() {
        // setup
        given(repository.findById(any(String.class))).willReturn(Optional.ofNullable(null));

        // execute
        List<Photo> photos = service.getEventTeamPhotos("1", "1");

        // assert
        assertNull(photos);
    }

    @Test
    public void testGetEventTeamPhotos_shouldReturnNullBecauseInvalidTeamId() {
        // setup
        Optional<Event> event = Optional.of(TestDataFactory.getEvent());

        given(repository.findById(any(String.class))).willReturn(event);

        // execute
        List<Photo> photos = service.getEventTeamPhotos("1", "non-existing team id");

        // assert
        assertNull(photos);
    }

    @Test
    public void testGetEventTeamPhotos_shouldReturnEventTeamPhotos() {
        // setup
        Optional<Event> event = Optional.of(TestDataFactory.getEvent());

        given(repository.findById(any(String.class))).willReturn(event);

        // execute
        List<Photo> photos = service.getEventTeamPhotos("1", "team1");

        // assert
        assertNotNull(photos);
    }

    @Test
    public void testValidateTeamId_shouldReturnFalse() {
        // setup
        Event event = TestDataFactory.getEvent();

        // execute
        boolean valid = EventServiceImpl.validateTeamId(event, "non-existing team id");

        // assert
        assertFalse(valid);
    }

    @Test
    public void testValidateTeamId_shouldReturnTrue() {
        // setup
        Event event = TestDataFactory.getEvent();

        // execute
        boolean valid = EventServiceImpl.validateTeamId(event, event.getTeams().get(0).getId());

        // assert
        assertTrue(valid);
    }

    @Test
    public void testDeleteEvent_shouldReturnNullBecauseEventNotFound() {
        // setup
        given(repository.findById(any(String.class))).willReturn(Optional.ofNullable(null));

        // execute
        Event deleted = service.deleteEvent("1");

        // assert
        assertNull(deleted);
    }

    @Test
    public void testDeleteEvent_shouldReturnDeletedEvent() {
        // setup
        Event event = TestDataFactory.getEvent();

        given(repository.findById(any(String.class))).willReturn(Optional.of(event));

        // execute
        Event deleted = service.deleteEvent(event.getId());

        // assert
        assertNotNull(deleted);
        assertEquals(event.getId(), deleted.getId());
    }
}
