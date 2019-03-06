package com.clickbait.orient.service;

import com.clickbait.orient.database.EventRepository;
import com.clickbait.orient.dto.UserDTO;
import com.clickbait.orient.model.Checkpoint;
import com.clickbait.orient.model.Event;
import com.clickbait.orient.model.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
        Event event = new Event(
                "1",
                "Le Event 1",
                2,
                Arrays.asList(
                        new Checkpoint("1", "First", new BigDecimal(10), new BigDecimal(10)),
                        new Checkpoint("2", "Second", new BigDecimal(20), new BigDecimal(20))),
                2,
                Arrays.asList(
                        new Team("team1", "Team One", Arrays.asList(new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH"), new UserDTO("id2", "karpis@gmail.com", "Karpis", "Karsis"))),
                        new Team("team2", "Team Two", Arrays.asList(new UserDTO("id3", "stotele@inbox.lt", "Stoteles", "Darbininke"), new UserDTO("id4", "bulka@ktu.edu", "Flex", "Tape")))
                )
        );

        given(repository.findById(event.getId())).willReturn(Optional.of(event));

        // execute
        Event returned = service.getEventById(event.getId());

        // assert
        assertNotNull(returned);
        assertEquals(returned.getId(), event.getId());
        assertEquals(returned.getName(), event.getName());
        assertEquals(returned.getCheckpointCount(), event.getCheckpointCount());
        assertEquals(returned.getTeamSize(), event.getTeamSize());

        // added cast for to long because two methods are available to be called here
        // (one with params (Object, Object) and one with (long, long)
        // since checkpoint count and team size can be an object or an number the
        // compiler doesn't know which to use
        assertEquals((long) returned.getCheckpointCount(), (long) returned.getCheckpoints().size());
        assertEquals((long) returned.getTeamSize(), (long) returned.getTeams().size());
    }

    @Test
    public void testGetAllEvents_shouldReturnEmptyList() {
        // setup
        List<Event> events = new ArrayList<>();

        given(repository.findAll()).willReturn(events);

        // execute
        List<Event> returned = service.getAllEvents();

        // assert
        assertNotNull(returned);
        assertEquals(0, returned.size());
    }

    @Test
    public void testGetAllEvents_shouldReturnFilledList() {
        // setup
        List<Event> events = Arrays.asList(new Event(
                "1",
                "Le Event 1",
                2,
                Arrays.asList(
                        new Checkpoint("1", "First", new BigDecimal(10), new BigDecimal(10)),
                        new Checkpoint("2", "Second", new BigDecimal(20), new BigDecimal(20))),
                2,
                Arrays.asList(
                        new Team("team1", "Team One", Arrays.asList(new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH"), new UserDTO("id2", "karpis@gmail.com", "Karpis", "Karsis"))),
                        new Team("team2", "Team Two", Arrays.asList(new UserDTO("id3", "stotele@inbox.lt", "Stoteles", "Darbininke"), new UserDTO("id4", "bulka@ktu.edu", "Flex", "Tape")))
                )
        ));

        given(repository.findAll()).willReturn(events);

        // execute
        List<Event> returned = service.getAllEvents();

        // assert
        assertNotNull(returned);
        assertEquals(1, returned.size());
        assertNotNull(returned.get(0));
        assertEquals(returned.get(0).getId(), events.get(0).getId());
        assertEquals(returned.get(0).getName(), events.get(0).getName());
        assertEquals(returned.get(0).getCheckpointCount(), events.get(0).getCheckpointCount());
        assertEquals(returned.get(0).getTeamSize(), events.get(0).getTeamSize());

        // added cast for to long because two methods are available to be called here
        // (one with params (Object, Object) and one with (long, long)
        // since checkpoint count and team size can be an object or an number the
        // compiler doesn't know which to use
        assertEquals((long) returned.get(0).getCheckpointCount(), (long) returned.get(0).getCheckpoints().size());
        assertEquals((long) returned.get(0).getTeamSize(), (long) returned.get(0).getTeams().size());
    }
}
