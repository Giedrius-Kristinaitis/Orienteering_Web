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

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
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

        Optional<User> optional = Optional.ofNullable(null);

        given(repository.findByEmail(user.getEmail())).willReturn(optional);

        // execute
        UserDTO returned = service.authenticateUser(user);

        // assert
        assertEquals(null, returned);
    }

    @Test
    public void testAuthenticateUser_shouldReturnNullBecauseWrongPassword() {
        // setup
        User userInDatabase = new User("", "email@email.com", "password", "", "");

        Optional<User> returned = Optional.of(userInDatabase);

        given(repository.findByEmail(userInDatabase.getEmail())).willReturn(returned);

        User userPassedToMethod = new User("", "email@email.com", "wrong password mate", "", "");

        // execute
        UserDTO userDTO = service.authenticateUser(userPassedToMethod);

        // assert
        assertEquals(null, userDTO);
    }

    @Test
    public void testAuthenticateUser_shouldReturnAuthenticatedUser() {
        // setup
        User user = new User("1", "email@email.com", "password", "aaaaaa", "bbbbbbb");

        Optional<User> returned = Optional.of(user);

        given(repository.findByEmail(user.getEmail())).willReturn(returned);

        // execute
        UserDTO userDTO = service.authenticateUser(user);

        // assert
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getFirstName(), userDTO.getFirstName());
        assertEquals(user.getLastName(), userDTO.getLastName());
    }

    @Test
    public void testRegisterUser_shouldReturnNullBecauseUserExistsWithEmail() {
        // setup
        User user = new User("le id", "", "", "", "");

        Optional<User> optional = Optional.of(user);

        given(repository.findByEmail(any(String.class))).willReturn(optional);

        // execute
        UserDTO registered = service.registerUser(user);

        // assert
        assertNull(registered);
    }

    @Test
    public void testRegisterUser_shouldReturnRegisteredUser() {
        // setup
        User user = new User("1", "email@email.com", "password", "aaaaaa", "bbbbbbb");

        given(repository.findByEmail(any(String.class))).willReturn(Optional.ofNullable(null));
        given(repository.save(any(User.class))).willReturn(user);

        // execute
        UserDTO registered = service.registerUser(user);

        // assert
        assertNotNull(registered);
        assertEquals(user.getId(), registered.getId());
        assertEquals(user.getEmail(), registered.getEmail());
        assertEquals(user.getFirstName(), registered.getFirstName());
        assertEquals(user.getLastName(), registered.getLastName());
    }
}
