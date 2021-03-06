package com.clickbait.orient.controller;

import com.clickbait.orient.TestDataFactory;
import com.clickbait.orient.database.UserRepository;
import com.clickbait.orient.dto.UserDTO;
import com.clickbait.orient.model.Event;
import com.clickbait.orient.model.Team;
import com.clickbait.orient.model.User;
import com.clickbait.orient.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EventService service;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ModelMapper modelMapper;

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
                .andExpect(jsonPath("$.photos", hasSize(1)))
                .andExpect(jsonPath("$.owner.id", is("id1")));
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

    @Test
    public void testGetEventTeamPhotos_shouldReturnNotFound() throws Exception {
        // setup
        given(service.getEventById(any(String.class))).willReturn(null);

        // execute and assert
        mvc.perform(get("/api/event/photos/1/1").accept("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetEventTeamPhotos_shouldReturnBadRequest() throws Exception {
        // setup
        Event event = TestDataFactory.getEvent();

        given(service.getEventById(any(String.class))).willReturn(event);

        // execute and assert
        mvc.perform(get("/api/event/photos/1/non-existing-team-id").accept("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetEventTeamPhotos_shouldReturnPhotos() throws Exception {
        // setup
        Event event = TestDataFactory.getEvent();

        given(service.getEventById(any(String.class))).willReturn(event);
        given(service.getEventTeamPhotos(any(String.class), any(String.class))).willReturn(event.getPhotos());

        // execute and assert
        mvc.perform(get("/api/event/photos/1/team1").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
  
    @Test  
    public void testAddEvent_shouldReturnConflict() throws Exception {
        // setup
        Event event = TestDataFactory.getEvent();

        given(service.getEventById(event.getId())).willReturn(event);

        // execute and assert
        mvc.perform(post("/api/event/" + event.getOwner().getId()).accept("application/json")
                .contentType("application/json")
                .characterEncoding("utf-8")
                .content(TestDataFactory.getValidEventAsJson()))
                .andExpect(status().isConflict());
    }

    @Test
    public void testAddEvent_shouldFailValidation() throws Exception {
        // execute and assert
        mvc.perform(post("/api/event/id1").accept("application/json")
                .contentType("application/json")
                .characterEncoding("utf-8")
                .content(TestDataFactory.getInvalidEventAsJson()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddEvent_shouldReturnBadRequestBecauseBadOwnerId() throws Exception {
        // setup
        given(service.getEventById(any(String.class))).willReturn(null);
        given(userRepository.findById(any(String.class))).willReturn(Optional.ofNullable(null));

        // execute and assert
        mvc.perform(post("/api/event/bad_owner_id").accept("application/json")
                .contentType("application/json")
                .characterEncoding("utf-8")
                .content(TestDataFactory.getValidEventAsJsonNoCreatedDateAndStatus()))
                .andExpect(status().isBadRequest());
    }
  
    @Test      
    public void testAddEvent_shouldReturnInsertedEvent() throws Exception {
        // setup
        Event event = TestDataFactory.getEvent();

        User owner = new User(event.getOwner().getId(), event.getOwner().getEmail(), "", event.getOwner().getFirstName(), event.getOwner().getLastName());

        given(service.getEventById(event.getId())).willReturn(null);
        given(service.saveEvent(any(Event.class))).willReturn(event);
        given(userRepository.findById(any(String.class))).willReturn(Optional.of(owner));
        given(modelMapper.map(any(), any())).willReturn(event.getOwner());

        // execute and assert
        mvc.perform(post("/api/event/" + owner.getId()).accept("application/json")
                .contentType("application/json")
                .characterEncoding("utf-8")
                .content(TestDataFactory.getValidEventAsJsonNoCreatedDateAndStatus()))
                .andExpect(status().isCreated())
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
                .andExpect(jsonPath("$.photos", hasSize(1)))
                .andExpect(jsonPath("$.owner.id", is(event.getOwner().getId())));
    }

    @Test
    public void testUpdateEvent_shouldReturnBadRequestBecauseFailedValidation() throws Exception {
        // execute and assert
        mvc.perform(put("/api/event/1").accept("application/json")
                .contentType("application/json")
                .characterEncoding("utf-8")
                .content(TestDataFactory.getInvalidEventAsJson()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateEvent_shouldReturnNotFound() throws Exception {
        // setup
        given(service.getEventById(any(String.class))).willReturn(null);

        // execute and assert
        mvc.perform(put("/api/event/1").accept("application/json")
                .contentType("application/json")
                .characterEncoding("utf-8")
                .content(TestDataFactory.getValidEventAsJson()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateEvent_shouldReturnUpdatedEvent() throws Exception {
        // setup
        Event event = TestDataFactory.getEvent();

        given(service.getEventById(any(String.class))).willReturn(event);
        given(service.saveEvent(any(Event.class))).willReturn(event);

        // execute and assert
        mvc.perform(put("/api/event/1").accept("application/json")
                .contentType("application/json")
                .characterEncoding("utf-8")
                .content(TestDataFactory.getValidEventAsJson()))
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
    public void testDeleteEvent_shouldReturnNotFound() throws Exception {
        // setup
        given(service.deleteEvent(any(String.class))).willReturn(null);

        // execute and assert
        mvc.perform(delete("/api/event/1").accept("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteEvent_shouldReturnDeletedEvent() throws Exception {
        // setup
        Event event = TestDataFactory.getEvent();

        given(service.deleteEvent(any(String.class))).willReturn(event);

        // execute and assert
        mvc.perform(delete("/api/event/1").accept("application/json"))
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
    public void testCreateTeam_shouldReturnBadRequest() throws Exception {
        // setup
        given(service.getEventById(any(String.class))).willReturn(null);

        // execute and assert
        mvc.perform(post("/api/event/team/1").accept("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateTeam_shouldReturnCreatedTeam() throws Exception {
        // setup
        Event event = TestDataFactory.getEvent();
        Team team = TestDataFactory.getTeam();

        given(service.getEventById(any(String.class))).willReturn(event);
        given(service.createTeam(any(String.class), any(Team.class))).willReturn(team);

        // execute and assert
        mvc.perform(post("/api/event/team/" + event.getId()).accept("application/json").characterEncoding("utf-8").contentType("application/json").content("{}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(team.getId())))
                .andExpect(jsonPath("$.name", is(team.getName())))
                .andExpect(jsonPath("$.members", hasSize(team.getMembers().size())))
                .andExpect(jsonPath("$.checkedCheckpoints", hasSize(team.getCheckedCheckpoints().size())));
    }

    @Test
    public void testGetTeam_shouldReturnBadRequestBecauseBadEventId() throws Exception {
        // setup
        given(service.getEventById(any(String.class))).willReturn(null);

        // execute and assert
        mvc.perform(get("/api/event/team/1/1").accept("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetTeam_shouldReturnNotFound() throws Exception {
        // setup
        Event event = TestDataFactory.getEvent();

        given(service.getEventById(any(String.class))).willReturn(event);
        given(service.getTeam(any(String.class), any(String.class))).willReturn(null);

        // execute and assert
        mvc.perform(get("/api/event/team/" + event.getId() + "/1").accept("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetTeam_shouldReturnTeam() throws Exception {
        // setup
        Event event = TestDataFactory.getEvent();
        Team team = TestDataFactory.getTeam();

        given(service.getEventById(any(String.class))).willReturn(event);
        given(service.getTeam(any(String.class), any(String.class))).willReturn(team);

        // execute and assert
        mvc.perform(get("/api/event/team/" + event.getId() + "/" + team.getId()).accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(team.getId())))
                .andExpect(jsonPath("$.name", is(team.getName())))
                .andExpect(jsonPath("$.members", hasSize(team.getMembers().size())))
                .andExpect(jsonPath("$.checkedCheckpoints", hasSize(team.getCheckedCheckpoints().size())));
    }

    @Test
    public void testAddTeamMember_shouldReturnBadRequestBecauseBadEventOrTeam() throws Exception {
        // setup
        given(service.addTeamMember(any(String.class), any(String.class), any(UserDTO.class))).willReturn(null);

        // execute and assert
        mvc.perform(post("/api/event/team/member/1/1").accept("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddTeamMember_shouldAddTeamMember() throws Exception {
        // setup
        UserDTO user = TestDataFactory.getUserDTO();

        given(service.addTeamMember(any(String.class), any(String.class), any(UserDTO.class))).willReturn(user);

        // execute and assert
        mvc.perform(post("/api/event/team/member/1/1").accept("application/json").characterEncoding("utf-8").contentType("application/json").content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(user.getId())))
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())));
    }

    @Test
    public void testRemoveTeamMember_shouldReturnBadRequestBecauseBadEventOrTeam() throws Exception {
        // setup
        given(service.removeTeamMember(any(String.class), any(String.class), any(String.class))).willReturn(null);

        // execute and assert
        mvc.perform(delete("/api/event/team/member/1/1/1").accept("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testRemoveTeamMember_shouldReturnRemovedMember() throws Exception {
        // setup
        UserDTO user = TestDataFactory.getUserDTO();

        given(service.removeTeamMember(any(String.class), any(String.class), any(String.class))).willReturn(user);

        // execute and assert
        mvc.perform(delete("/api/event/team/member/1/1/1").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(user.getId())))
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())));
    }
}
