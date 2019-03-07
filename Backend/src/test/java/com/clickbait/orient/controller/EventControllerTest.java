package com.clickbait.orient.controller;

import com.clickbait.orient.dto.UserDTO;
import com.clickbait.orient.model.Checkpoint;
import com.clickbait.orient.model.Event;
import com.clickbait.orient.model.EventStatus;
import com.clickbait.orient.model.Team;
import com.clickbait.orient.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EventService service;

    @Test
    public void testGetEvent_shouldReturnNotFound() throws Exception {
        // setup
        String id = "id";

        given(service.getEventById(id)).willReturn(null);

        // execute and assert
        mvc.perform(get("/api/event/" + id).accept("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetEvent_shouldReturnEvent() throws Exception {
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
                ),
                new Date(),
                EventStatus.OPEN
        );

        given(service.getEventById(event.getId())).willReturn(event);

        // execute and assert
        mvc.perform(get("/api/event/" + event.getId()).accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(event.getId())))
                .andExpect(jsonPath("$.name", is(event.getName())))
                .andExpect(jsonPath("$.checkpointCount", is(event.getCheckpointCount())))
                .andExpect(jsonPath("$.teamSize", is(event.getTeamSize())))
                .andExpect(jsonPath("$.checkpoints", hasSize(2)))
                .andExpect(jsonPath("$.teams", hasSize(2)))
                .andExpect(jsonPath("$.status", is("Open")))
                .andExpect(jsonPath("$.created", anything()));
    }

    @Test
    public void testGetAllEvents_shouldReturnNotFound() throws Exception {
        // setup
        given(service.getAllEvents()).willReturn(new ArrayList<Event>());

        // execute and assert
        mvc.perform(get("/api/event").accept("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllEvents_shouldReturnEventList() throws Exception {
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
                ),
                new Date(),
                EventStatus.OPEN
        ));

        given(service.getAllEvents()).willReturn(events);

        // execute and assert
        mvc.perform(get("/api/event").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(events.get(0).getId())))
                .andExpect(jsonPath("$[0].name", is(events.get(0).getName())))
                .andExpect(jsonPath("$[0].checkpointCount", is(events.get(0).getCheckpointCount())))
                .andExpect(jsonPath("$[0].teamSize", is(events.get(0).getTeamSize())))
                .andExpect(jsonPath("$[0].checkpoints", hasSize(2)))
                .andExpect(jsonPath("$[0].teams", hasSize(2)))
                .andExpect(jsonPath("$[0].status", is("Open")))
                .andExpect(jsonPath("$[0].created", anything()));
    }
}
