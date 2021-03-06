package com.clickbait.orient;

import com.clickbait.orient.dto.UserDTO;
import com.clickbait.orient.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Provides data to be used in tests to prevent code repetition
 */
public class TestDataFactory {

    /**
     * Creates a single event
     * @return event
     */
    public static Event getEvent() {
        List<Photo> photos = new ArrayList<>();

        photos.add(new Photo(
                "1",
                "team1",
                "1",
                "http://blahblah.com/alksjdlkad.jpg",
                "image/png",
                100L
        ));

        List<String> checkedIn = new ArrayList<>();
        checkedIn.add("1");

        List<UserDTO> teamMembers1 = new ArrayList<>();
        teamMembers1.addAll(Arrays.asList(new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH"), new UserDTO("id2", "karpis@gmail.com", "Karpis", "Karsis")));

        List<UserDTO> teamMembers2 = new ArrayList<>();
        teamMembers2.addAll(Arrays.asList(new UserDTO("id3", "stotele@inbox.lt", "Stoteles", "Darbininke"), new UserDTO("id4", "bulka@ktu.edu", "Flex", "Tape")));

        List<Team> teams = new ArrayList<>();
        teams.addAll(Arrays.asList(
                new Team("team1", "Team One", teamMembers1, checkedIn),
                new Team("team2", "Team Two", teamMembers2, checkedIn)
        ));

        return new Event(
                "1",
                "Le Event 1",
                "This is just an event",
                2,
                Arrays.asList(
                        new Checkpoint("1", "First", new BigDecimal(10), new BigDecimal(10)),
                        new Checkpoint("2", "Second", new BigDecimal(20), new BigDecimal(20))),
                2,
                teams,
                new Date(),
                new Date(),
                EventStatus.OPEN,
                (long) 2*60*60*1000, // 2 hours * 60 minutes * 60 seconds * 1000 milliseconds
                2500,
                photos,
                new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH")
        );
    }

    /**
     * Gets a team
     * @return
     */
    public static Team getTeam() {
        List<String> checkedIn = new ArrayList<>();
        checkedIn.add("1");

        List<UserDTO> teamMembers = new ArrayList<>();
        teamMembers.addAll(Arrays.asList(new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH"), new UserDTO("id2", "karpis@gmail.com", "Karpis", "Karsis")));

        return new Team("team1", "Team One", teamMembers, checkedIn);
    }

    /**
     * Gets a UserDTO
     * @return
     */
    public static UserDTO getUserDTO() {
        return new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH");
    }

    /**
     * Creates an event list
     *
     * @param size number of events
     *
     * @return event list
     */
    public static List<Event> getEventList(int size) {
        List<Event> events = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            events.add(getEvent());
        }

        return events;
    }

    /**
     * Creates a page of events
     * @return event page
     */
    public static Page<Event> getEventPage(int size) {
        List<Event> eventList = getEventList(size);
        Page<Event> page = new PageImpl<Event>(eventList, PageRequest.of(0, eventList.size()), eventList.size());

        return page;
    }

    /**
     * Gets a single valid event in JSON format
     * @return event in JSON
     */
    public static String getValidEventAsJson() {
        return "{\"id\": \"1\", \"name\": \"Le Event 1\", \"description\": \"This is just an event\", \"checkpointCount\": 2, \"checkpoints\": [], \"teamSize\": 2, \"teams\": [], \"created\": \"2019-09-28\", \"starting\": \"2019-11-12\", \"status\": \"Open\", \"estimatedTimeMillis\": 7200000, \"estimatedDistanceMetres\": 2500, \"photos\": []}";
    }

    /**
     * Gets a single valid event in JSON format without created date and status
     * @return event in JSON
     */
    public static String getValidEventAsJsonNoCreatedDateAndStatus() {
        return "{\"id\": \"1\", \"name\": \"Le Event 1\", \"description\": \"This is just an event\", \"checkpointCount\": 2, \"checkpoints\": [], \"teamSize\": 2, \"teams\": [], \"starting\": \"2019-11-12\", \"estimatedTimeMillis\": 7200000, \"estimatedDistanceMetres\": 2500, \"photos\": []}";
    }

    /**
     * Gets a single invalid event in JSON format
     * @return invalid event in JSON
     */
    public static String getInvalidEventAsJson() {
        return "{\"id\": \"1\", \"name\": \"\", \"description\": \"\", \"checkpointCount\": 0, \"teamSize\": 1, \"teams\": [], \"created\": \"2019-09-28\", \"starting\": \"\", \"status\": \"Open\", \"estimatedTimeMillis\": -1, \"estimatedDistanceMetres\": -1, \"photos\": []}";
    }
}
