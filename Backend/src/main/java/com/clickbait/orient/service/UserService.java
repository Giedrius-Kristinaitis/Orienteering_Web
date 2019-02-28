package com.clickbait.orient.service;

import com.clickbait.orient.database.UserRepository;
import com.clickbait.orient.dto.UserDTO;
import com.clickbait.orient.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles user operations
 */
@Service
public class UserService {

    // model mapper used to map models to dto
    private ModelMapper modelMapper;

    // handles CRUD database operations
    private UserRepository userRepository;

    /**
     * Default class constructor
     */
    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Authenticates a user
     *
     * @param user user to authenticate
     * @return authenticated user dto if authentication was successful, null otherwise
     */
    public UserDTO authenticateUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            User existing = userRepository.findByEmail(user.getEmail());

            // check if the password is correct
            if (existing.getPassword().equals(user.getPassword())) {
                return modelMapper.map(existing, UserDTO.class);
            }
        }

        return null;
    }
}
