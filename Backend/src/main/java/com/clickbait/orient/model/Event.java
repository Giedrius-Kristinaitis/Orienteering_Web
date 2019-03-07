package com.clickbait.orient.model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Event representation
 */
@SuppressWarnings("unused")
public class Event {

    @Id
    private String id;

    @NotBlank
    private String name;

    @Min(1)
    @NotNull
    private Integer checkpointCount;

    @NotNull
    private List<Checkpoint> checkpoints;

    @Min(1)
    @NotNull
    private Integer teamSize;

    @NotNull
    private List<Team> teams;

    @NotNull
    private Date created;

    private EventStatus status;

    /**
     * Default no-args constructor
     */
    public Event() {}

    /**
     * Constructor with arguments
     *
     * @param id
     * @param name
     * @param checkpointCount
     * @param checkpoints
     * @param teamSize
     * @param teams
     */
    public Event(String id, String name, Integer checkpointCount, List<Checkpoint> checkpoints, Integer teamSize, List<Team> teams, Date created, EventStatus status) {
        this.id = id;
        this.name = name;
        this.checkpointCount = checkpointCount;
        this.checkpoints = checkpoints;
        this.teamSize = teamSize;
        this.teams = teams;
        this.created = created;
        this.status = status;
    }

    // GETTERS
    public String getId() { return id; }
    public String getName() { return name; }
    public Integer getCheckpointCount() { return checkpointCount; }
    public List<Checkpoint> getCheckpoints() { return checkpoints; }
    public Integer getTeamSize() { return teamSize; }
    public List<Team> getTeams() { return teams; }
    public Date getCreated() { return created; }
    public EventStatus getStatus() { return status; }

    // SETTERS
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCheckpointCount(Integer checkpointCount) { this.checkpointCount = checkpointCount; }
    public void setCheckpoints(List<Checkpoint> checkpoints) { this.checkpoints = checkpoints; }
    public void setTeamSize(Integer teamSize) { this.teamSize = teamSize; }
    public void setTeams(List<Team> teams) { this.teams = teams; }
    public void setCreated(Date created) { this.created = created; }
    public void setStatus(EventStatus status) { this.status = status; }
}
