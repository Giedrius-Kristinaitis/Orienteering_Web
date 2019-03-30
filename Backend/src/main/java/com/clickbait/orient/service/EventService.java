package com.clickbait.orient.service;

import com.clickbait.orient.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Event service abstraction
 */
@Service
public interface EventService {

    /**
     * Gets a single event
     *
     * @param id id of the event
     * @return event with the specified id
     */
    Event getEventById(String id);

    /**
     * Gets all existing events
     * @param pageNumber which page to get
     * @param pageSize size of one page
     * @return event page
     */
    Page<Event> getAllEvents(Integer pageNumber, Integer pageSize);

    /**
     * Adds or updates an event
     *
     * @param event event to add/update
     * @return added/updated event
     */
    Event saveEvent(Event event);
}
