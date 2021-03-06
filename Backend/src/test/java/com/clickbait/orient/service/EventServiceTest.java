package com.clickbait.orient.service;

import com.clickbait.orient.TestDataFactory;
import com.clickbait.orient.database.EventRepository;
import com.clickbait.orient.database.UserRepository;
import com.clickbait.orient.dto.UserDTO;
import com.clickbait.orient.model.Event;
import com.clickbait.orient.model.Photo;
import com.clickbait.orient.model.Team;
import com.clickbait.orient.model.User;
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

    @MockBean
    private UserRepository userRepository;

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
    public void testValidateCheckpointId_shouldReturnFalse() {
        // setup
        Event event = TestDataFactory.getEvent();

        // execute
        boolean valid = EventServiceImpl.validateCheckpointId(event, "non-existing checkpoint id");

        // assert
        assertFalse(valid);
    }

    @Test
    public void testValidateCheckpointId_shouldReturnTrue() {
        // setup
        Event event = TestDataFactory.getEvent();

        // execute
        boolean valid = EventServiceImpl.validateCheckpointId(event, "1");

        // assert
        assertTrue(valid);
    }

    @Test
    public void testCheckIn_shouldCheckIn() {
        // setup
        Event event = TestDataFactory.getEvent();

        int oldCheckedInCheckpointCount = event.getTeams().get(0).getCheckedCheckpoints().size();

        // execute
        EventServiceImpl.checkIn(event, "team1", "1");

        int newCheckedInCheckpointCount = event.getTeams().get(0).getCheckedCheckpoints().size();

        // assert
        assertEquals(oldCheckedInCheckpointCount + 1, newCheckedInCheckpointCount);
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

    @Test
    public void testCreateTeam_shouldReturnNullBecauseBadEvent() {
        // setup
        given(repository.findById(any(String.class))).willReturn(Optional.ofNullable(null));

        // execute
        Team created = service.createTeam("not important", null); // team can be null here because it doesn't matter in this test

        // assert
        assertNull(created);
    }

    @Test
    public void testCreateTeam_shouldCreateTeam() {
        // setup
        Event event = TestDataFactory.getEvent();
        Team team = TestDataFactory.getTeam();

        given(repository.findById(any(String.class))).willReturn(Optional.of(event));

        // execute
        Team created = service.createTeam(event.getId(), team);

        // assert
        assertNotNull(created);
        assertNotNull(created.getId());
    }

    @Test
    public void testGetTeam_shouldReturnNullBecauseBadEvent() {
        // setup
        given(repository.findById(any(String.class))).willReturn(Optional.ofNullable(null));

        // execute
        Team team = service.getTeam("not important", "not important");

        // assert
        assertNull(team);
    }

    @Test
    public void testGetTeam_shouldReturnNullBecauseBadTeamId() {
        // setup
        Event event = TestDataFactory.getEvent();

        given(repository.findById(any(String.class))).willReturn(Optional.of(event));

        // execute
        Team team = service.getTeam(event.getId(), "non-existing team id");

        // assert
        assertNull(team);
    }

    @Test
    public void testGetTeam_shouldReturnTeam() {
        // setup
        Event event = TestDataFactory.getEvent();

        given(repository.findById(any(String.class))).willReturn(Optional.of(event));

        // execute
        Team team = service.getTeam(event.getId(), event.getTeams().get(0).getId());

        // assert
        assertNotNull(team);
    }

    @Test
    public void testAddTeamMember_shouldReturnNullBecauseBadTeamOrEvent() {
        // setup
        given(repository.findById(any(String.class))).willReturn(Optional.ofNullable(null));

        UserDTO user = TestDataFactory.getUserDTO();

        // execute
        UserDTO added = service.addTeamMember("non-existing event", "non-existing team", user);

        // assert
        assertNull(added);
    }

    @Test
    public void testAddTeamMember_shouldReturnNullBecauseBadUserId() {
        // setup
        Event event = TestDataFactory.getEvent();

        given(repository.findById(any(String.class))).willReturn(Optional.of(event));
        given(userRepository.findById(any(String.class))).willReturn(Optional.ofNullable(null));

        UserDTO user = TestDataFactory.getUserDTO();

        // execute
        UserDTO added = service.addTeamMember("non-existing event", "non-existing team", user);

        // assert
        assertNull(added);
    }

    @Test
    public void testAddTeamMember_shouldAddTeamMember() {
        // setup
        Event event = TestDataFactory.getEvent();

        given(repository.findById(any(String.class))).willReturn(Optional.of(event));
        given(userRepository.findById(any(String.class))).willReturn(Optional.of(new User()));

        UserDTO user = TestDataFactory.getUserDTO();

        // execute
        UserDTO added = service.addTeamMember(event.getId(), event.getTeams().get(0).getId(), user);

        // assert
        assertNotNull(added);
    }

    @Test
    public void testRemoveTeamMember_shouldReturnNullBecauseBadEventOrTeam() {
        // setup
        given(repository.findById(any(String.class))).willReturn(Optional.ofNullable(null));

        UserDTO user = TestDataFactory.getUserDTO();

        // execute
        UserDTO removed = service.removeTeamMember("non-existing event", "non-existing team", user.getId());

        // assert
        assertNull(removed);
    }

    @Test
    public void testRemoveTeamMember_shouldRemoveMember() {
        // setup
        Event event = TestDataFactory.getEvent();

        given(repository.findById(any(String.class))).willReturn(Optional.of(event));

        UserDTO user = TestDataFactory.getUserDTO();

        // execute
        UserDTO removed = service.removeTeamMember(event.getId(), event.getTeams().get(0).getId(), user.getId());

        // assert
        assertNotNull(removed);
    }
}
