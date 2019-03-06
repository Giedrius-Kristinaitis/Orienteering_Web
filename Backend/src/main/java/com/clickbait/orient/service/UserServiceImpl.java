package com.clickbait.orient.service;

import com.clickbait.orient.database.UserRepository;
import com.clickbait.orient.dto.UserDTO;
import com.clickbait.orient.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * Handles user operations
 */
public class UserServiceImpl implements UserService {

    // model mapper used to map models to dto
    private ModelMapper modelMapper;

    // handles CRUD database operations
    private UserRepository userRepository;

    /**
     * Default class constructor
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
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
        Optional<User> optional = userRepository.findByEmail(user.getEmail());

        // check if the optional contains any value
        if (optional.isPresent()) {
            // check if the password is correct
            if (optional.get().getPassword().equals(user.getPassword())) {
                return modelMapper.map(optional.get(), UserDTO.class);
            }
        }

        // if this piece of code is reached that means the user was not found
        // or the password was incorrect

        return null;
    }
}
