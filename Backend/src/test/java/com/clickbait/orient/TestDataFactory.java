package com.clickbait.orient;

import com.clickbait.orient.dto.UserDTO;
import com.clickbait.orient.model.Checkpoint;
import com.clickbait.orient.model.Event;
import com.clickbait.orient.model.EventStatus;
import com.clickbait.orient.model.Team;
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
        return new Event(
                "1",
                "Le Event 1",
                "This is just an event",
                2,
                Arrays.asList(
                        new Checkpoint("1", "First", new BigDecimal(10), new BigDecimal(10)),
                        new Checkpoint("2", "Second", new BigDecimal(20), new BigDecimal(20))),
                2,
                Arrays.asList(
                        new Team("team1", "Team One", Arrays.asList(new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH"), new UserDTO("id2", "karpis@gmail.com", "Karpis", "Karsis"))),
                        new Team("team2", "Team Two", Arrays.asList(new UserDTO("id3", "stotele@inbox.lt", "Stoteles", "Darbininke"), new UserDTO("id4", "bulka@ktu.edu", "Flex", "Tape")))
                ),
                new Date(),
                new Date(),
                EventStatus.OPEN,
                (long) 2*60*60*1000, // 2 hours * 60 minutes * 60 seconds * 1000 milliseconds
                2500,
                Arrays.asList("http://blahblahblah.net/asasdad.jpg", "http://thisisparta.com/asd.png")
        );
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
     * Gets a single invalid event in JSON format
     * @return invalid event in JSON
     */
    public static String getInvalidEventAsJson() {
        return "{\"id\": \"1\", \"name\": \"\", \"description\": \"\", \"checkpointCount\": 0, \"teamSize\": 1, \"teams\": [], \"created\": \"2019-09-28\", \"starting\": \"\", \"status\": \"Open\", \"estimatedTimeMillis\": -1, \"estimatedDistanceMetres\": -1, \"photos\": []}";
    }
}
