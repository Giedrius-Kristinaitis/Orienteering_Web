package com.clickbait.orient.controller;

import com.clickbait.orient.TestDataFactory;
import com.clickbait.orient.model.Event;
import com.clickbait.orient.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
        Event event = TestDataFactory.getEvent();

        given(service.getEventById(event.getId())).willReturn(event);

        // execute and assert
        mvc.perform(get("/api/event/" + event.getId()).accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(event.getId())))
                .andExpect(jsonPath("$.name", is(event.getName())))
                .andExpect(jsonPath("$.description", is(event.getDescription())))
                .andExpect(jsonPath("$.checkpointCount", is(event.getCheckpointCount())))
                .andExpect(jsonPath("$.teamSize", is(event.getTeamSize())))
                .andExpect(jsonPath("$.checkpoints", hasSize(2)))
                .andExpect(jsonPath("$.teams", hasSize(2)))
                .andExpect(jsonPath("$.status", is("Open")))
                .andExpect(jsonPath("$.created", anything()))
                .andExpect(jsonPath("$.starting", anything()))
                .andExpect(jsonPath("$.estimatedTimeMillis", is(7200000)))
                .andExpect(jsonPath("$.estimatedDistanceMetres", is(2500)))
                .andExpect(jsonPath("$.photos", hasSize(1)));
    }

    @Test
    public void testGetEventPage_shouldReturnNotFound() throws Exception {
        // setup
        given(service.getAllEvents(0, 1)).willReturn(null);

        // execute and assert
        mvc.perform(get("/api/event/page/0/1").accept("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetEventPage_shouldReturnEventPage() throws Exception {
        // setup
        Page<Event> events = TestDataFactory.getEventPage(1);

        given(service.getAllEvents(0, 1)).willReturn(events);

        // execute and assert
        mvc.perform(get("/api/event/page/0/1").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.events", hasSize(1)))
                .andExpect(jsonPath("$.events[0].id", is(events.getContent().get(0).getId())))
                .andExpect(jsonPath("$.events[0].name", is(events.getContent().get(0).getName())))
                .andExpect(jsonPath("$.events[0].description", is(events.getContent().get(0).getDescription())))
                .andExpect(jsonPath("$.events[0].checkpointCount", is(events.getContent().get(0).getCheckpointCount())))
                .andExpect(jsonPath("$.events[0].teamSize", is(events.getContent().get(0).getTeamSize())))
                .andExpect(jsonPath("$.events[0].checkpoints", hasSize(2)))
                .andExpect(jsonPath("$.events[0].teams", hasSize(2)))
                .andExpect(jsonPath("$.events[0].status", is("Open")))
                .andExpect(jsonPath("$.events[0].created", anything()))
                .andExpect(jsonPath("$.events[0].starting", anything()))
                .andExpect(jsonPath("$.events[0].estimatedTimeMillis", is(7200000)))
                .andExpect(jsonPath("$.events[0].estimatedDistanceMetres", is(2500)))
                .andExpect(jsonPath("$.events[0].photos", hasSize(1)))
                .andExpect(jsonPath("$.totalElements", is(1)))
                .andExpect(jsonPath("$.pageSize", is(1)))
                .andExpect(jsonPath("$.totalPages", is(1)));
    }

    @Test
    public void testGetEventPage_shouldReturnPageWith5Events() throws Exception {
        // setup
        Page<Event> events = TestDataFactory.getEventPage(5);

        given(service.getAllEvents(0, 5)).willReturn(events);

        // execute and assert
        mvc.perform(get("/api/event/page/0/5").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.events", hasSize(5)))
                .andExpect(jsonPath("$.totalElements", is(5)))
                .andExpect(jsonPath("$.pageSize", is(5)))
                .andExpect(jsonPath("$.totalPages", anything()));
    }

    @Test
    public void testGetEventPageInvalidPathVariables_shouldReturnBadRequest() throws Exception {
        // execute and assert
        mvc.perform(get("/api/event/page/-1/1").accept("application/json"))
                .andExpect(status().isBadRequest());
    }
}
