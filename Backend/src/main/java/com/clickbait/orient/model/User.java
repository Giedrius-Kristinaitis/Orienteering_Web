package com.clickbait.orient.model;

import com.clickbait.orient.validation.OnRegistration;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * User model class
 */
@SuppressWarnings("unused")
public class User {

    @Id
    private String id;

    @NotBlank
    @Size(min = 6)
    private String email;

    @NotBlank(groups = OnRegistration.class)
    private String firstName;

    @NotBlank(groups = OnRegistration.class)
    private String lastName;

    @NotBlank
    @Size(min = 5)
    private String password;

    /**
     * Default no-args constructor
     */
    public User() { }

    /**
     * Constructor with arguments
     */
    public User(String id, String email, String password, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // GETTERS
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    // SETTERS
    public void setId(String id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}
