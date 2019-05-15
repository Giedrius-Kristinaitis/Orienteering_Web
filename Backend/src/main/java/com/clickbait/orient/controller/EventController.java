package com.clickbait.orient.controller;

import com.clickbait.orient.dto.UserDTO;
import com.clickbait.orient.model.*;
import com.clickbait.orient.service.EventService;
import com.clickbait.orient.service.EventServiceImpl;
import com.clickbait.orient.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Handles requests related to events
 */
@RestController
@RequestMapping("/api/event")
public class EventController {

    private EventService service;
    
    private UserRepository userRepository;
    
    private ModelMapper modelMapper;

    /**
     * Default class constructor
     * @param service
     */
    @Autowired
    public EventController(EventService service, UserRepository userRepository, ModelMapper modelMapper) {
        this.service = service;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Gets a single event with specified id
     * @param id id of the event
     * @return event
     */
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable String id) {
        Event event = service.getEventById(id);

        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    /**
     * Gets all events.
     * Note: page numbers start from 0
     * @return event list
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    public ResponseEntity<EventsResponse> getAllEvents(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        // check if parameters are valid
        if (pageNumber < 0 || pageSize <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Page<Event> events = service.getAllEvents(pageNumber, pageSize);

        if (events == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        EventsResponse response = new EventsResponse(events.getContent(), events.getTotalElements(), events.getSize(), events.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Gets all event team's photos
     *
     * @param eventId
     * @param teamId
     * @return
     */
    @GetMapping("/photos/{eventId}/{teamId}")
    public ResponseEntity<List<Photo>> getEventTeamPhotos(@PathVariable String eventId, @PathVariable String teamId) {
        Event event = service.getEventById(eventId);

        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!EventServiceImpl.validateTeamId(event, teamId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Photo> photos = service.getEventTeamPhotos(eventId, teamId);

        return new ResponseEntity<>(photos, HttpStatus.OK);
    }
  
    /**
     * Adds an event to the event list
     *
     * @param event event to add
     * @param ownerId who created the event
     *
     * @return added event
     */
    @PostMapping("/{ownerId}")
    public ResponseEntity<Event> addEvent(@Valid @RequestBody Event event, @PathVariable String ownerId) {
        if (service.getEventById(event.getId()) != null) {
            // event already exists, return conflict
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        event.setStatus(EventStatus.OPEN);
        event.setCreated(new Date());
        
        Optional<User> foundUser = userRepository.findById(ownerId);
        
        if (!foundUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        UserDTO owner = modelMapper.map(foundUser.get(), UserDTO.class);

        event.setOwner(owner);
        
        Event addedEvent = service.saveEvent(event);

        return new ResponseEntity<>(addedEvent, HttpStatus.CREATED);
    }

    /**
     * Updates an event
     *
     * @param event new event data
     * @param id id of the event
     * @return updated event
     */
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@Valid @RequestBody Event event, @PathVariable String id) {
        if (service.getEventById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Event updatedEvent = service.saveEvent(event);

        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    /**
     * Deletes an event
     *
     * @param id id of the event to delete
     * @return deleted event
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable String id) {
        Event deleted = service.deleteEvent(id);

        if (deleted == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    /**
     * Creates a new team
     *
     * @param eventId event to add the team to
     * @param team team to create
     * @return created team
     */
    @PostMapping("/team/{eventId}")
    public ResponseEntity<Team> createTeam(@PathVariable String eventId, @RequestBody Team team) {
        if (service.getEventById(eventId) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Team created = service.createTeam(eventId, team);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Gets a team
     *
     * @param eventId id of the event the team belongs to
     * @param teamId id of the team
     * @return team with the specified id
     */
    @GetMapping("/team/{eventId}/{teamId}")
    public ResponseEntity<Team> getTeam(@PathVariable String eventId, @PathVariable String teamId) {
        if (service.getEventById(eventId) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Team team = service.getTeam(eventId, teamId);

        if (team == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    /**
     * Adds a team member
     *
     * @param eventId id of the event
     * @param teamId id of the team
     * @param user member to add
     * @return added member
     */
    @PostMapping("/team/member/{eventId}/{teamId}")
    public ResponseEntity<UserDTO> addTeamMember(@PathVariable String eventId, @PathVariable String teamId, @RequestBody UserDTO user) {
        UserDTO added = service.addTeamMember(eventId, teamId, user);

        if (added == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(added, HttpStatus.OK);
    }

    /**
     * Removes a team member
     *
     * @param eventId id of the event
     * @param teamId id of the team
     * @param userId id of the member to remove
     * @return removed member
     */
    @DeleteMapping("/team/member/{eventId}/{teamId}/{userId}")
    public ResponseEntity<UserDTO> removeTeamMember(@PathVariable String eventId, @PathVariable String teamId, @PathVariable String userId) {
        UserDTO removed = service.removeTeamMember(eventId, teamId, userId);

        if (removed == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(removed, HttpStatus.OK);
    }
}
