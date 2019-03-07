package com.clickbait.orient.controller;

import com.clickbait.orient.model.Event;
import com.clickbait.orient.model.EventsResponse;
import com.clickbait.orient.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
