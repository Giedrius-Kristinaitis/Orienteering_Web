package com.clickbait.orient.service;

import com.clickbait.orient.database.EventRepository;
import com.clickbait.orient.dto.UserDTO;
import com.clickbait.orient.model.Checkpoint;
import com.clickbait.orient.model.Event;
import com.clickbait.orient.model.Photo;
import com.clickbait.orient.model.Team;
import com.clickbait.orient.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Manages event operations
 */
public class EventServiceImpl implements EventService {

    // repo to CRUD events
    private EventRepository repository;
    
    private UserRepository userRepository;

    /**
     * Default class constructor
     * @param repository event repository
     */
    @Autowired
    public EventServiceImpl(EventRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userReopsitory = userRepository;
    }

    /**
     * Gets a single event
     * @param id id of the event
     * @return event with the specified id
     */
    public Event getEventById(String id) {
        if (id == null) {
            return null;
        }

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

    /**
     * Gets all photos of a single team
     *
     * @param eventId id of the event
     * @param teamId  id of the team
     * @return team photos
     */
    @Override
    public List<Photo> getEventTeamPhotos(String eventId, String teamId) {
        Event event = getEventById(eventId);
      
        if (event == null) {
            return null;
        }
      
        if (!validateTeamId(event, teamId)) {
            return null;
        }

        return event.getPhotos();
    }

    /**
     * Adds or updates an event
     *
     * @param event event to add/update
     * @return added/updated event
     */
    @Override
    public Event saveEvent(Event event) {
        if (event == null) {
            return null;
        }
      
        return repository.save(event);
    }

    /**
     * Checks if the team id exists in the given event
     *
     * @param event
     * @param teamId
     * @return
     */
    public static boolean validateTeamId(Event event, String teamId) {
        for (Team team: event.getTeams()) {
            if (team.getId().equals(teamId)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the checkpoint id exists in the given event
     *
     * @param event
     * @param checkpointId
     * @return
     */
    public static boolean validateCheckpointId(Event event, String checkpointId) {
        for (Checkpoint checkpoint: event.getCheckpoints()) {
            if (checkpoint.getId().equals(checkpointId)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Marks a checkpoint as checked-in
     *
     * @param event
     * @param teamId
     * @param checkpointId
     */
    public static void checkIn(Event event, String teamId, String checkpointId) {
        // get the team object
        Team team = getTeamById(event, teamId);

        team.getCheckedCheckpoints().add(checkpointId);
    }

    /**
     * Gets a team in an event
     *
     * @param event
     * @param teamId
     * @return
     */
    private static Team getTeamById(Event event, String teamId) {
        Team team = null;

        for (Team t: event.getTeams()) {
            if (t.getId().equals(teamId)) {
                team = t;
                break;
            }
        }

        return team;
    }
    /**
     * Deletes an event
     *
     * @param id id of the event
     * @return deleted event
     */
    @Override
    public Event deleteEvent(String id) {
        Optional<Event> existing = repository.findById(id);

        if (!existing.isPresent()) {
            return null;
        }

        repository.deleteById(id);

        return existing.get();
    }

    /**
     * Creates a new team
     *
     * @param eventId id of the event to which the team belongs
     * @param team    team to create
     * @return created team
     */
    @Override
    public Team createTeam(String eventId, Team team) {
        Event event = getEventById(eventId);

        if (event == null) {
            return null;
        }

        team.setId(String.valueOf(System.currentTimeMillis()));

        event.getTeams().add(team);

        return team;
    }

    /**
     * Gets a team by it's id
     *
     * @param eventId id of the event to which the team belongs
     * @param id      id of the team
     * @return team object
     */
    @Override
    public Team getTeam(String eventId, String id) {
        Event event = getEventById(eventId);

        if (event == null) {
            return null;
        }

        return getTeamById(event, id);
    }

    /**
     * Adds a new member to a team
     *
     * @param eventId id of the event
     * @param teamId  id of the team
     * @param member  member to add
     * @return added member
     */
    @Override
    public UserDTO addTeamMember(String eventId, String teamId, UserDTO member) {
        Team team = getTeamInEvent(eventId, teamId);

        if (team == null) {
            return null;
        }
        
        if (!userRepository.findById(member.getId())) {
            return null;
        }

        team.getMembers().add(member);

        return member;
    }

    /**
     * Removes a team member
     *
     * @param eventId id of the event
     * @param teamId  id of the team
     * @param userId  id of the user to be removed
     * @return removed member
     */
    @Override
    public UserDTO removeTeamMember(String eventId, String teamId, String userId) {
        Team team = getTeamInEvent(eventId, teamId);

        if (team == null) {
            return null;
        }

        return removeTeamMember(team, userId);
    }

    /**
     * Validates a team, as in checks if it exists in the given event
     *
     * @param eventId
     * @param teamId
     * @return non-null team object if the team exists
     */
    private Team getTeamInEvent(String eventId, String teamId) {
        Event event = getEventById(eventId);

        if (event == null) {
            return null;
        }

        return getTeamById(event, teamId);
    }

    /**
     * Removes a team member
     *
     * @param team
     * @param userId
     * @return
     */
    private UserDTO removeTeamMember(Team team, String userId) {
        UserDTO removed = null;

        for (int i = 0; i < team.getMembers().size(); i++) {
            if (team.getMembers().get(i).getId().equals(userId)) {
                removed = team.getMembers().get(i);
                team.getMembers().remove(i);
                break;
            }
        }

        return removed;
    }
}
