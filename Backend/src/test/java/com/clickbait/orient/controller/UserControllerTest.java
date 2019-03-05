package com.clickbait.orient.controller;

import com.clickbait.orient.dto.UserDTO;
import com.clickbait.orient.model.User;
import com.clickbait.orient.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @Test
    public void testLoginValidValues_shouldReturnOk() throws Exception {
        // initialize
        UserDTO user = new UserDTO("id1", "email@email.com", "First", "Last");

        given(service.authenticateUser(any(User.class))).willReturn(user);

        // execute and assert
        mvc.perform(post("/user/login")
            .contentType("application/json")
            .characterEncoding("utf-8")
            .content("{\"email\": \"email@email.com\", \"password\": \"password\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(user.getId())))
            .andExpect(jsonPath("$.email", is(user.getEmail())))
            .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
            .andExpect(jsonPath("$.lastName", is(user.getLastName())));
    }

    @Test
    public void testLoginInvalidValues_shouldReturnBadRequest() throws Exception {
        // execute and assert
        mvc.perform(post("/user/login")
            .contentType("application/json")
            .characterEncoding("utf-8")
            .content("{\"email\": \"ee\", \"password\": \"1234\"}"))
            .andExpect(status().isBadRequest());
    }
}
