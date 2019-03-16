package com.clickbait.orient.service;

import com.clickbait.orient.TestDataFactory;
import com.clickbait.orient.database.EventRepository;
import com.clickbait.orient.model.Event;
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
        assertEquals(returned.getId(), event.getId());
        assertEquals(returned.getName(), event.getName());
        assertEquals(returned.getDescription(), event.getDescription());
        assertEquals(returned.getCheckpointCount(), event.getCheckpointCount());
        assertEquals(returned.getTeamSize(), event.getTeamSize());
        assertEquals(returned.getCreated().toString(), event.getCreated().toString());
        assertEquals(returned.getStarting().toString(), event.getStarting().toString());
        assertEquals(returned.getStatus(), event.getStatus());
        assertEquals(returned.getEstimatedDistanceMetres(), event.getEstimatedDistanceMetres());
        assertEquals(returned.getEstimatedTimeMillis(), event.getEstimatedTimeMillis());

        // added cast for to long because two methods are available to be called here
        // (one with params (Object, Object) and one with (long, long)
        // since checkpoint count and team size can be an object or an number the
        // compiler doesn't know which to use
        assertEquals((long) returned.getCheckpointCount(), (long) returned.getCheckpoints().size());
        assertEquals((long) returned.getTeamSize(), (long) returned.getTeams().size());
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
    public void testGetAllEvents_shouldReturnFilledPage() {
        // setup
        Page<Event> events = TestDataFactory.getEventPage();

        given(repository.findAll(any(Pageable.class))).willReturn(events);

        // execute
        Page<Event> page = service.getAllEvents(0, 1);

        // assert
        assertNotNull(page);

        assertNotNull(page.getContent());
        assertEquals(1, page.getContent().size());
        assertNotNull(page.getContent().get(0));
        assertEquals(page.getContent().get(0).getId(), events.getContent().get(0).getId());
        assertEquals(page.getContent().get(0).getName(), events.getContent().get(0).getName());
        assertEquals(page.getContent().get(0).getDescription(), events.getContent().get(0).getDescription());
        assertEquals(page.getContent().get(0).getCheckpointCount(), events.getContent().get(0).getCheckpointCount());
        assertEquals(page.getContent().get(0).getTeamSize(), events.getContent().get(0).getTeamSize());
        assertEquals(page.getContent().get(0).getCreated().toString(), events.getContent().get(0).getCreated().toString());
        assertEquals(page.getContent().get(0).getStarting().toString(), events.getContent().get(0).getStarting().toString());
        assertEquals(page.getContent().get(0).getStatus(), events.getContent().get(0).getStatus());
        assertEquals(page.getContent().get(0).getEstimatedDistanceMetres(), events.getContent().get(0).getEstimatedDistanceMetres());
        assertEquals(page.getContent().get(0).getEstimatedTimeMillis(), events.getContent().get(0).getEstimatedTimeMillis());

        // added cast for to long because two methods are available to be called here
        // (one with params (Object, Object) and one with (long, long)
        // since checkpoint count and team size can be an object or an number the
        // compiler doesn't know which to use
        assertEquals((long) page.getContent().get(0).getCheckpointCount(), (long) page.getContent().get(0).getCheckpoints().size());
        assertEquals((long) page.getContent().get(0).getTeamSize(), (long) page.getContent().get(0).getTeams().size());
    }
}
