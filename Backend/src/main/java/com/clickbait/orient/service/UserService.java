package com.clickbait.orient.service;

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

    // mock some users for now
    private User[] users = new User[] {
        new User("id1", "le_email@email.com", "password", "QWERTY", "ASDFGH"),
        new User("id2", "karpis@gmail.com", "password", "Karpis", "Karsis"),
        new User("id3", "stotele@inbox.lt", "password", "Stoteles", "Darbininke"),
        new User("id4", "bulka@ktu.edu", "password", "Flex", "Tape")
    };

    /**
     * Default class constructor
     */
    @Autowired
    public UserService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Authenticates a user
     *
     * @param user user to authenticate
     * @return authenticated user dto if authentication was successful, null otherwise
     */
    public UserDTO authenticateUser(User user) {
        for (User mockedUser: users) {
            if (mockedUser.getEmail().equals(user.getEmail()) && mockedUser.getPassword().equals(user.getPassword())) {
                return modelMapper.map(user, UserDTO.class);
            }
        }

        return null;
    }
}
