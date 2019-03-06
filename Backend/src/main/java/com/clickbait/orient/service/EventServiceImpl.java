package com.clickbait.orient.service;

import com.clickbait.orient.database.EventRepository;
import com.clickbait.orient.model.Event;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Manages event operations
 */
public class EventServiceImpl implements EventService {

    // repo to CRUD events
    private EventRepository repository;

    /**
     * Default class constructor
     * @param repository event repository
     */
    @Autowired
    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    /**
     * Gets a single event
     * @param id id of the event
     * @return event with the specified id
     */
    public Event getEventById(String id) {
        Optional<Event> event = repository.findById(id);

        // check if the event was found
        if (event.isPresent()) {
            return event.get();
        }

        return null;
    }

    /**
     * Gets all existing events
     * @return event list
     */
    public List<Event> getAllEvents() {
        return repository.findAll();
    }
}
