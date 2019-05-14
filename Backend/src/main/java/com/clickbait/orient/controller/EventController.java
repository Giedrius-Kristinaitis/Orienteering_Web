package com.clickbait.orient.controller;

import com.clickbait.orient.model.Event;
import com.clickbait.orient.model.EventStatus;
import com.clickbait.orient.model.EventsResponse;
import com.clickbait.orient.model.Photo;
import com.clickbait.orient.service.EventService;
import com.clickbait.orient.service.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Date;
import java.util.List;

/**
 * Handles requests related to events
 */
@RestController
@RequestMapping("/api/event")
public class EventController {

    private EventService service;

    /**
     * Default class constructor
     * @param service
     */
    @Autowired
    public EventController(EventService service) {
        this.service = service;
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
     * @return added event
     */
    @PostMapping
    public ResponseEntity<Event> addEvent(@Valid @RequestBody Event event) {
        if (service.getEventById(event.getId()) != null) {
            // event already exists, return conflict
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        event.setStatus(EventStatus.OPEN);
        event.setCreated(new Date());

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
}
