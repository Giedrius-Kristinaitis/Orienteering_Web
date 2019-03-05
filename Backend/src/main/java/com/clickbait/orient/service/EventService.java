package com.clickbait.orient.service;

import com.clickbait.orient.model.Event;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @return event list
     */
    List<Event> getAllEvents();
}
