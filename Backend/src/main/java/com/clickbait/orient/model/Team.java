package com.clickbait.orient.model;

import com.clickbait.orient.dto.UserDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Team representation
 */
@SuppressWarnings("unused")
public class Team {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotNull
    private List<UserDTO> members;

    private List<String> checkedCheckpoints;

    /**
     * Default no-args constructor
     */
    public Team() {}

    /**
     * Constructor with arguments
     * @param id
     * @param name
     * @param members
     * @param checkedCheckpoints
     */
    public Team(String id, String name, List<UserDTO> members, List<String> checkedCheckpoints) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.checkedCheckpoints = checkedCheckpoints;
    }

    // GETTERS
    public String getId() { return id; }
    public String getName() { return name; }
    public List<UserDTO> getMembers() { return members; }
    public List<String> getCheckedCheckpoints() { return checkedCheckpoints; }

    // SETTERS
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setMembers(List<UserDTO> members) { this.members = members; }
    public void setCheckedCheckpoints(List<String> checkedCheckpoints) { this.checkedCheckpoints = checkedCheckpoints; }
}
