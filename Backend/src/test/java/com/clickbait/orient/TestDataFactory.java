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
                2500
        );
    }

    /**
     * Creates an event list
     * @return event list
     */
    public static List<Event> getEventList() {
        return Arrays.asList(new Event(
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
                2500
        ));
    }

    /**
     * Creates a page of events
     * @return event page
     */
    public static Page<Event> getEventPage() {
        List<Event> eventList = getEventList();
        Page<Event> page = new PageImpl<Event>(eventList, PageRequest.of(0, eventList.size()), eventList.size());

        return page;
    }
}
