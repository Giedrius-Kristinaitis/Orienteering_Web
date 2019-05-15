package com.clickbait.orient.model;

import com.clickbait.orient.dto.UserDTO;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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

    @NotBlank
    private String description;

    @Min(1)
    @NotNull
    private Integer checkpointCount;

    @NotNull
    private List<Checkpoint> checkpoints;

    @Min(1)
    @NotNull
    private Integer teamSize;

    private List<Team> teams;

    private Date created;

    @NotNull
    private Date starting;

    private EventStatus status;

    @NotNull
    @Positive
    private Long estimatedTimeMillis;

    @NotNull
    @Positive
    private Integer estimatedDistanceMetres;

    private List<Photo> photos;

    private UserDTO owner;

    /**
     * Default no-args constructor
     */
    public Event() {}

    /**
     * Constructor with arguments
     * @param id
     * @param name
     * @param description
     * @param checkpointCount
     * @param checkpoints
     * @param teamSize
     * @param teams
     * @param created
     * @param starting
     * @param status
     * @param estimatedTimeMillis
     * @param estimatedDistanceMetres
     * @param photos
     * @param owner
     */
    public Event(String id, String name, String description, Integer checkpointCount, List<Checkpoint> checkpoints, Integer teamSize, List<Team> teams, Date created, Date starting, EventStatus status, Long estimatedTimeMillis, Integer estimatedDistanceMetres, List<Photo> photos, UserDTO owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.checkpointCount = checkpointCount;
        this.checkpoints = checkpoints;
        this.teamSize = teamSize;
        this.teams = teams;
        this.created = created;
        this.starting = starting;
        this.status = status;
        this.estimatedTimeMillis = estimatedTimeMillis;
        this.estimatedDistanceMetres = estimatedDistanceMetres;
        this.photos = photos;
        this.owner = owner;
    }

    // GETTERS
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Integer getCheckpointCount() { return checkpointCount; }
    public List<Checkpoint> getCheckpoints() { return checkpoints; }
    public Integer getTeamSize() { return teamSize; }
    public List<Team> getTeams() { return teams; }
    public Date getCreated() { return created; }
    public Date getStarting() { return starting; }
    public EventStatus getStatus() { return status; }
    public Long getEstimatedTimeMillis() { return estimatedTimeMillis; }
    public Integer getEstimatedDistanceMetres() { return estimatedDistanceMetres; }
    public List<Photo> getPhotos() { return photos; }
    public UserDTO getOwner() { return owner; }

    // SETTERS
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setCheckpointCount(Integer checkpointCount) { this.checkpointCount = checkpointCount; }
    public void setCheckpoints(List<Checkpoint> checkpoints) { this.checkpoints = checkpoints; }
    public void setTeamSize(Integer teamSize) { this.teamSize = teamSize; }
    public void setTeams(List<Team> teams) { this.teams = teams; }
    public void setCreated(Date created) { this.created = created; }
    public void setStarting(Date starting) { this.starting = starting; }
    public void setStatus(EventStatus status) { this.status = status; }
    public void setEstimatedTimeMillis(Long estimatedTimeMillis) { this.estimatedTimeMillis = estimatedTimeMillis; }
    public void setEstimatedDistanceMetres(Integer estimatedDistanceMetres) { this.estimatedDistanceMetres = estimatedDistanceMetres; }
    public void setPhotos(List<Photo> photos) { this.photos = photos; }
    public void setOwner(UserDTO owner) { this.owner = owner; }
}
