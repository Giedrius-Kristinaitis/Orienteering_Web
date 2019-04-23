package com.clickbait.orient.service;

import com.clickbait.orient.model.Event;
import com.clickbait.orient.model.Photo;
import org.springframework.data.domain.Page;
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
     * @param pageNumber which page to get
     * @param pageSize size of one page
     * @return event page
     */
    Page<Event> getAllEvents(Integer pageNumber, Integer pageSize);

    /**
     * Gets all photos of a single team
     *
     * @param eventId id of the event
     * @param teamId id of the team
     * @return team photos
     */
    List<Photo> getEventTeamPhotos(String eventId, String teamId);

    /*
     * Adds or updates an event
     *
     * @param event event to add/update
     * @return added/updated event
     */
    Event saveEvent(Event event);
}
