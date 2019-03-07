package com.clickbait.orient.service;

import com.clickbait.orient.database.EventRepository;
import com.clickbait.orient.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
     * @param pageNumber which page to get
     * @param pageSize size of one page
     * @return event page, null if the page is empty
     */
    public Page<Event> getAllEvents(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Event> page = repository.findAll(pageable);

        if (page == null || page.getNumberOfElements() == 0 || page.getContent().size() == 0) {
            return null;
        }

        return page;
    }
}
