package com.clickbait.orient.service;

import com.clickbait.orient.database.UserRepository;
import com.clickbait.orient.dto.UserDTO;
import com.clickbait.orient.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Test
    public void testAuthenticateUser_shouldReturnNullBecauseUserDoesntExist() {
        // setup
        User user = new User("", "email@email.com", "", "", "");

        given(repository.existsByEmail(user.getEmail())).willReturn(false);

        // execute
        UserDTO returned = service.authenticateUser(user);

        // assert
        assertEquals(null, returned);
    }

    @Test
    public void testAuthenticateUser_shouldReturnNullBecauseWrongPassword() {
        // setup
        User userInDatabase = new User("", "email@email.com", "password", "", "");

        given(repository.existsByEmail(userInDatabase.getEmail())).willReturn(true);
        given(repository.findByEmail(userInDatabase.getEmail())).willReturn(userInDatabase);

        User userPassedToMethod = new User("", "email@email.com", "wrong password mate", "", "");

        // execute
        UserDTO returned = service.authenticateUser(userPassedToMethod);

        // assert
        assertEquals(null, returned);
    }

    @Test
    public void testAuthenticateUser_shouldReturnAuthenticatedUser() {
        // setup
        User user = new User("1", "email@email.com", "password", "aaaaaa", "bbbbbbb");

        given(repository.existsByEmail(user.getEmail())).willReturn(true);
        given(repository.findByEmail(user.getEmail())).willReturn(user);

        // execute
        UserDTO returned = service.authenticateUser(user);

        // assert
        assertEquals(user.getId(), returned.getId());
        assertEquals(user.getEmail(), returned.getEmail());
        assertEquals(user.getFirstName(), returned.getFirstName());
        assertEquals(user.getLastName(), returned.getLastName());
    }
}
