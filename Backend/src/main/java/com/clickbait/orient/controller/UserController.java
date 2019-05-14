package com.clickbait.orient.controller;

import com.clickbait.orient.dto.UserDTO;
import com.clickbait.orient.model.User;
import com.clickbait.orient.service.UserService;
import com.clickbait.orient.validation.OnRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Handles requests related to user authentication
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    // handles user actions
    private UserService service;

    /**
     * Default class constructor
     * @param service
     */
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * Authenticates a user
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@Valid @RequestBody User user) {
        UserDTO userDto = service.authenticateUser(user);

        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /**
     * Registers a new user
     *
     * @param user user to register
     * @return registered user
     */
    @PostMapping("/sign-up")
    @Validated(OnRegistration.class)
    public ResponseEntity<UserDTO> register(@Valid @RequestBody User user) {
        UserDTO registered = service.registerUser(user);

        if (registered == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(registered, HttpStatus.OK);
    }
}
