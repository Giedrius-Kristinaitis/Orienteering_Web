package com.clickbait.orient.service;

import com.clickbait.orient.dto.UserDTO;
import com.clickbait.orient.model.User;
import org.springframework.stereotype.Service;

/**
 * User service abstraction
 */
@Service
public interface UserService {

    /**
     * Authenticates a user
     *
     * @param user user to authenticate
     * @return authenticated user dto if authentication was successful, null otherwise
     */
    UserDTO authenticateUser(User user);
}
