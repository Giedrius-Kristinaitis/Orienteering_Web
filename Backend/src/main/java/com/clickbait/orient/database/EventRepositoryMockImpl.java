package com.clickbait.orient.database;

import com.clickbait.orient.dto.UserDTO;
import com.clickbait.orient.model.*;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * Mock implementation for the event repository. Will be deleted later
 */
public class EventRepositoryMockImpl implements EventRepository {

    // used to generate ids
    private final Random random = new Random();

    // id characters
    private final char[] idCharacters = new char[] {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'r', 'p', 'o', 'n', 'm', 'l', 'k', 'j', 'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a'
    };

    // mock some events
    private final List<Event> events = new ArrayList<>();

    public EventRepositoryMockImpl() {
        List<Photo> photos = new ArrayList<>();

        photos.add(new Photo(
                "1",
                "team1",
                "1",
                "http://blahblah.com/alksjdlkad.jpg",
                "image/png",
                100L
        ));

        photos.add(new Photo(
                "1",
                "team2",
                "1",
                "http://jadsahdsc.net/aljsdcjadc.png",
                "image/png",
                100L
        ));

        List<String> checkedIn = new ArrayList<>();
        checkedIn.add("1");

        // add all events
        events.addAll(Arrays.asList(
                new Event(
                        "1",
                        "Le Event 1",
                        "This is just an event",
                        2,
                        Arrays.asList(
                                new Checkpoint("1", "First", new BigDecimal(10), new BigDecimal(10)),
                                new Checkpoint("2", "Second", new BigDecimal(20), new BigDecimal(20))),
                        2,
                        Arrays.asList(
                                new Team("team1", "Team One", Arrays.asList(new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH"), new UserDTO("id2", "karpis@gmail.com", "Karpis", "Karsis")), checkedIn),
                                new Team("team2", "Team Two", Arrays.asList(new UserDTO("id3", "stotele@inbox.lt", "Stoteles", "Darbininke"), new UserDTO("id4", "bulka@ktu.edu", "Flex", "Tape")), checkedIn)
                        ),
                        new Date(),
                        new Date(),
                        EventStatus.OPEN,
                        (long) 2*60*60*1000, // 2 hours * 60 minutes * 60 seconds * 1000 milliseconds
                        2500,
                        (List<Photo>) ((ArrayList<Photo>) photos).clone(),
                        new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH")
                ),

                new Event(
                        "2",
                        "Le Event 2",
                        "This is just an event",
                        2,
                        Arrays.asList(
                                new Checkpoint("1", "First", new BigDecimal(10), new BigDecimal(10)),
                                new Checkpoint("2", "Second", new BigDecimal(20), new BigDecimal(20))),
                        2,
                        Arrays.asList(
                                new Team("team1", "Team One", Arrays.asList(new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH"), new UserDTO("id2", "karpis@gmail.com", "Karpis", "Karsis")), checkedIn),
                                new Team("team2", "Team Two", Arrays.asList(new UserDTO("id3", "stotele@inbox.lt", "Stoteles", "Darbininke"), new UserDTO("id4", "bulka@ktu.edu", "Flex", "Tape")), checkedIn)
                        ),
                        new Date(),
                        new Date(),
                        EventStatus.OPEN,
                        (long) 2*60*60*1000, // 2 hours * 60 minutes * 60 seconds * 1000 milliseconds
                        2500,
                        (List<Photo>) ((ArrayList<Photo>) photos).clone(),
                        new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH")
                ),

                new Event(
                        "3",
                        "Le Event 3",
                        "This is just an event",
                        2,
                        Arrays.asList(
                                new Checkpoint("1", "First", new BigDecimal(10), new BigDecimal(10)),
                                new Checkpoint("2", "Second", new BigDecimal(20), new BigDecimal(20))),
                        2,
                        Arrays.asList(
                                new Team("team1", "Team One", Arrays.asList(new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH"), new UserDTO("id2", "karpis@gmail.com", "Karpis", "Karsis")), checkedIn),
                                new Team("team2", "Team Two", Arrays.asList(new UserDTO("id3", "stotele@inbox.lt", "Stoteles", "Darbininke"), new UserDTO("id4", "bulka@ktu.edu", "Flex", "Tape")), checkedIn)
                        ),
                        new Date(),
                        new Date(),
                        EventStatus.OPEN,
                        (long) 2*60*60*1000, // 2 hours * 60 minutes * 60 seconds * 1000 milliseconds
                        2500,
                        (List<Photo>) ((ArrayList<Photo>) photos).clone(),
                        new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH")
                ),

                new Event(
                        "4",
                        "Le Event 4",
                        "This is just an event",
                        2,
                        Arrays.asList(
                                new Checkpoint("1", "First", new BigDecimal(10), new BigDecimal(10)),
                                new Checkpoint("2", "Second", new BigDecimal(20), new BigDecimal(20))),
                        2,
                        Arrays.asList(
                                new Team("team1", "Team One", Arrays.asList(new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH"), new UserDTO("id2", "karpis@gmail.com", "Karpis", "Karsis")), checkedIn),
                                new Team("team2", "Team Two", Arrays.asList(new UserDTO("id3", "stotele@inbox.lt", "Stoteles", "Darbininke"), new UserDTO("id4", "bulka@ktu.edu", "Flex", "Tape")), checkedIn)
                        ),
                        new Date(),
                        new Date(),
                        EventStatus.OPEN,
                        (long) 2*60*60*1000, // 2 hours * 60 minutes * 60 seconds * 1000 milliseconds
                        2500,
                        (List<Photo>) ((ArrayList<Photo>) photos).clone(),
                        new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH")
                ),

                new Event(
                        "5",
                        "Le Event 5",
                        "This is just an event",
                        2,
                        Arrays.asList(
                                new Checkpoint("1", "First", new BigDecimal(10), new BigDecimal(10)),
                                new Checkpoint("2", "Second", new BigDecimal(20), new BigDecimal(20))),
                        2,
                        Arrays.asList(
                                new Team("team1", "Team One", Arrays.asList(new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH"), new UserDTO("id2", "karpis@gmail.com", "Karpis", "Karsis")), checkedIn),
                                new Team("team2", "Team Two", Arrays.asList(new UserDTO("id3", "stotele@inbox.lt", "Stoteles", "Darbininke"), new UserDTO("id4", "bulka@ktu.edu", "Flex", "Tape")), checkedIn)
                        ),
                        new Date(),
                        new Date(),
                        EventStatus.OPEN,
                        (long) 2*60*60*1000, // 2 hours * 60 minutes * 60 seconds * 1000 milliseconds
                        2500,
                        (List<Photo>) ((ArrayList<Photo>) photos).clone(),
                        new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH")
                ),

                new Event(
                        "6",
                        "Le Event 6",
                        "This is just an event",
                        2,
                        Arrays.asList(
                                new Checkpoint("1", "First", new BigDecimal(10), new BigDecimal(10)),
                                new Checkpoint("2", "Second", new BigDecimal(20), new BigDecimal(20))),
                        2,
                        Arrays.asList(
                                new Team("team1", "Team One", Arrays.asList(new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH"), new UserDTO("id2", "karpis@gmail.com", "Karpis", "Karsis")), checkedIn),
                                new Team("team2", "Team Two", Arrays.asList(new UserDTO("id3", "stotele@inbox.lt", "Stoteles", "Darbininke"), new UserDTO("id4", "bulka@ktu.edu", "Flex", "Tape")), checkedIn)
                        ),
                        new Date(),
                        new Date(),
                        EventStatus.OPEN,
                        (long) 2*60*60*1000, // 2 hours * 60 minutes * 60 seconds * 1000 milliseconds
                        2500,
                        (List<Photo>) ((ArrayList<Photo>) photos).clone(),
                        new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH")
                ),

                new Event(
                        "7",
                        "Le Event 7",
                        "This is just an event",
                        2,
                        Arrays.asList(
                                new Checkpoint("1", "First", new BigDecimal(10), new BigDecimal(10)),
                                new Checkpoint("2", "Second", new BigDecimal(20), new BigDecimal(20))),
                        2,
                        Arrays.asList(
                                new Team("team1", "Team One", Arrays.asList(new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH"), new UserDTO("id2", "karpis@gmail.com", "Karpis", "Karsis")), checkedIn),
                                new Team("team2", "Team Two", Arrays.asList(new UserDTO("id3", "stotele@inbox.lt", "Stoteles", "Darbininke"), new UserDTO("id4", "bulka@ktu.edu", "Flex", "Tape")), checkedIn)
                        ),
                        new Date(),
                        new Date(),
                        EventStatus.OPEN,
                        (long) 2*60*60*1000, // 2 hours * 60 minutes * 60 seconds * 1000 milliseconds
                        2500,
                        (List<Photo>) ((ArrayList<Photo>) photos).clone(),
                        new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH")
                ),

                new Event(
                        "8",
                        "Le Event 8",
                        "This is just an event",
                        2,
                        Arrays.asList(
                                new Checkpoint("1", "First", new BigDecimal(10), new BigDecimal(10)),
                                new Checkpoint("2", "Second", new BigDecimal(20), new BigDecimal(20))),
                        2,
                        Arrays.asList(
                                new Team("team1", "Team One", Arrays.asList(new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH"), new UserDTO("id2", "karpis@gmail.com", "Karpis", "Karsis")), checkedIn),
                                new Team("team2", "Team Two", Arrays.asList(new UserDTO("id3", "stotele@inbox.lt", "Stoteles", "Darbininke"), new UserDTO("id4", "bulka@ktu.edu", "Flex", "Tape")), checkedIn)
                        ),
                        new Date(),
                        new Date(),
                        EventStatus.OPEN,
                        (long) 2*60*60*1000, // 2 hours * 60 minutes * 60 seconds * 1000 milliseconds
                        2500,
                        (List<Photo>) ((ArrayList<Photo>) photos).clone(),
                        new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH")
                ),

                new Event(
                        "9",
                        "Le Event 9",
                        "This is just an event",
                        2,
                        Arrays.asList(
                                new Checkpoint("1", "First", new BigDecimal(10), new BigDecimal(10)),
                                new Checkpoint("2", "Second", new BigDecimal(20), new BigDecimal(20))),
                        2,
                        Arrays.asList(
                                new Team("team1", "Team One", Arrays.asList(new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH"), new UserDTO("id2", "karpis@gmail.com", "Karpis", "Karsis")), checkedIn),
                                new Team("team2", "Team Two", Arrays.asList(new UserDTO("id3", "stotele@inbox.lt", "Stoteles", "Darbininke"), new UserDTO("id4", "bulka@ktu.edu", "Flex", "Tape")), checkedIn)
                        ),
                        new Date(),
                        new Date(),
                        EventStatus.OPEN,
                        (long) 2*60*60*1000, // 2 hours * 60 minutes * 60 seconds * 1000 milliseconds
                        2500,
                        (List<Photo>) ((ArrayList<Photo>) photos).clone(),
                        new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH")
                ),

                new Event(
                        "10",
                        "Le Event 10",
                        "This is just an event",
                        2,
                        Arrays.asList(
                                new Checkpoint("1", "First", new BigDecimal(10), new BigDecimal(10)),
                                new Checkpoint("2", "Second", new BigDecimal(20), new BigDecimal(20))),
                        2,
                        Arrays.asList(
                                new Team("team1", "Team One", Arrays.asList(new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH"), new UserDTO("id2", "karpis@gmail.com", "Karpis", "Karsis")), checkedIn),
                                new Team("team2", "Team Two", Arrays.asList(new UserDTO("id3", "stotele@inbox.lt", "Stoteles", "Darbininke"), new UserDTO("id4", "bulka@ktu.edu", "Flex", "Tape")), checkedIn)
                        ),
                        new Date(),
                        new Date(),
                        EventStatus.OPEN,
                        (long) 2*60*60*1000, // 2 hours * 60 minutes * 60 seconds * 1000 milliseconds
                        2500,
                        (List<Photo>) ((ArrayList<Photo>) photos).clone(),
                        new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH")
                ),

                new Event(
                        "11",
                        "Le Event 11",
                        "This is just an event",
                        2,
                        Arrays.asList(
                                new Checkpoint("1", "First", new BigDecimal(10), new BigDecimal(10)),
                                new Checkpoint("2", "Second", new BigDecimal(20), new BigDecimal(20))),
                        2,
                        Arrays.asList(
                                new Team("team1", "Team One", Arrays.asList(new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH"), new UserDTO("id2", "karpis@gmail.com", "Karpis", "Karsis")), checkedIn),
                                new Team("team2", "Team Two", Arrays.asList(new UserDTO("id3", "stotele@inbox.lt", "Stoteles", "Darbininke"), new UserDTO("id4", "bulka@ktu.edu", "Flex", "Tape")), checkedIn)
                        ),
                        new Date(),
                        new Date(),
                        EventStatus.OPEN,
                        (long) 2*60*60*1000, // 2 hours * 60 minutes * 60 seconds * 1000 milliseconds
                        2500,
                        (List<Photo>) ((ArrayList<Photo>) photos).clone(),
                        new UserDTO("id1", "le_email@email.com", "QWERTY", "ASDFGH")
                )));
    }

    @Override
    public Optional<Event> findById(String s) {
        for (Event event: events) {
            if (event.getId().equals(s)) {
                return Optional.of(event);
            }
        }

        return Optional.ofNullable(null);
    }

    @Override
    public Page<Event> findAll(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();

        if (offset >= events.size()) {
            return null;
        }

        List<Event> pagedEvents = new ArrayList<>();

        for (int i = offset; i < offset + pageable.getPageSize(); i++) {
            if (i > events.size() - 1) {
                break;
            }

            pagedEvents.add(events.get(i));
        }

        Page<Event> page = new PageImpl<Event>(pagedEvents, pageable, events.size());

        return page;
    }

    @Override
    public <S extends Event> S save(S s) {
        if (s.getId() == null) {
            s.setId(generateId(10));
            events.add(s);
        } else {
            Optional<Event> optional = findById(s.getId());

            if (optional.isPresent()) {
                Event event = optional.get();

                event.setName(s.getName());
                event.setDescription(s.getDescription());
                event.setCheckpointCount(s.getCheckpointCount());
                event.setCheckpoints(s.getCheckpoints());
                event.setTeamSize(s.getTeamSize());
                event.setTeams(s.getTeams());
                event.setCreated(s.getCreated());
                event.setStarting(s.getStarting());
                event.setEstimatedTimeMillis(s.getEstimatedTimeMillis());
                event.setEstimatedDistanceMetres(s.getEstimatedDistanceMetres());
                event.setPhotos(s.getPhotos());
                event.setStatus(s.getStatus());
            } else {
                events.add(s);
            }
        }

        return s;
    }

    private String generateId(int length) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            builder.append(idCharacters[random.nextInt(idCharacters.length)]);
        }

        return builder.toString();
    }

    @Override
    public void deleteById(String id) {
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);

            if (event.getId().equals(id)) {
                events.remove(i);
                break;
            }
        }
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Event> findAll() {
        return events;
    }

    @Override
    public <S extends Event> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Iterable<Event> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Event event) {

    }

    @Override
    public void deleteAll(Iterable<? extends Event> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Event> findAll(Sort sort) {
        return null;
    }

    @Override
    public <S extends Event> S insert(S s) {
        return null;
    }

    @Override
    public <S extends Event> List<S> insert(Iterable<S> iterable) {
        return null;
    }

    @Override
    public <S extends Event> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Event> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Event> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Event> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Event> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Event> boolean exists(Example<S> example) {
        return false;
    }
}
