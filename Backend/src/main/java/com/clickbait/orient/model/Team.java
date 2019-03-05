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

    /**
     * Default no-args constructor
     */
    public Team() {}

    /**
     * Constructor with arguments
     * @param id
     * @param name
     * @param members
     */
    public Team(String id, String name, List<UserDTO> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    // GETTERS
    public String getId() { return id; }
    public String getName() { return name; }
    public List<UserDTO> getMembers() { return members; }

    // SETTERS
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setMembers(List<UserDTO> members) { this.members = members; }
}
