package com.clickbait.orient.service;

import com.clickbait.orient.dto.UserDTO;
import com.clickbait.orient.model.Event;
import com.clickbait.orient.model.Photo;
import com.clickbait.orient.model.Team;
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

    /**
     * Adds or updates an event
     *
     * @param event event to add/update
     * @return added/updated event
     */
    Event saveEvent(Event event);

    /**
     * Deletes an event
     *
     * @param id id of the event
     * @return deleted event
     */
    Event deleteEvent(String id);

    /**
     * Creates a new team
     *
     * @param eventId id of the event to which the team belongs
     * @param team team to create
     * @return created team
     */
    Team createTeam(String eventId, Team team);

    /**
     * Gets a team by it's id
     *
     * @param eventId id of the event to which the team belongs
     * @param id id of the team
     * @return team object
     */
    Team getTeam(String eventId, String id);

    /**
     * Adds a new member to a team
     *
     * @param eventId id of the event
     * @param teamId id of the team
     * @param member member to add
     * @return added member
     */
    UserDTO addTeamMember(String eventId, String teamId, UserDTO member);

    /**
     * Removes a team member
     *
     * @param eventId id of the event
     * @param teamId id of the team
     * @param userId id of the user to be removed
     * @return removed member
     */
    UserDTO removeTeamMember(String eventId, String teamId, String userId);
}
