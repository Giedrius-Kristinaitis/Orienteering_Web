package com.clickbait.orient.dto;

/**
 * User Data Transfer Object. Used to send user information back to the client
 */
@SuppressWarnings("unused")
public class UserDTO {

    private String id;
    private String email;
    private String firstName;
    private String lastName;

    /**
     * Default no-args constructor
     */
    public UserDTO() {}

    /**
     * Constructor with arguments
     */
    public UserDTO(String id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // GETTERS AND SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
