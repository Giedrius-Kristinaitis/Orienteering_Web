package com.clickbait.orient.controller;

import com.clickbait.orient.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.multipart.MultipartFile;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FileController.class)
public class FileControllerTest {

    @MockBean
    private FileService service;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testUploadPhoto_shouldReturnBadRequest() throws Exception {
        // setup
        given(service.savePhoto(any(MultipartFile.class), any(String.class), any(String.class), any(String.class))).willReturn(null);

        MockMultipartFile file = new MockMultipartFile("file", "picture.png", "image/png", new byte[100]);

        // execute and assert
        mvc.perform(multipart("/api/file/photo/1/1/1").file(file))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUploadPhoto_shouldReturnPhotoResponse() throws Exception {
        // setup
        String downloadURL = "http://localhost:8080/api/file/photo/picture.png";
        String eventId = "1";
        String teamId = "1";
        String checkpointId = "1";
        int fileSize = 100;
        String fileType = "image/png";

        given(service.savePhoto(any(MultipartFile.class), any(String.class), any(String.class), any(String.class))).willReturn(downloadURL);

        MockMultipartFile file = new MockMultipartFile("file", "picture.png", fileType, new byte[fileSize]);

        // execute and assert
        mvc.perform(multipart("/api/file/photo/" + eventId + "/1/1").file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.downloadURL", is(downloadURL)))
                .andExpect(jsonPath("$.eventId", is(eventId)))
                .andExpect(jsonPath("$.teamId", is(teamId)))
                .andExpect(jsonPath("$.checkpointId", is(checkpointId)))
                .andExpect(jsonPath("$.fileType", is(fileType)))
                .andExpect(jsonPath("$.fileSize", is(fileSize)));
    }

    @Test
    public void testGetPhoto_shouldReturnNotFound() throws Exception {
        // setup
        given(service.getPhoto(any(String.class))).willReturn(null);

        // execute and assert
        mvc.perform(get("/api/file/photo/photo.png"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetPhoto_shouldReturnPhoto() throws Exception {
        // setup
        ByteArrayResource resource = new ByteArrayResource(new byte[100]);

        given(service.getPhoto(any(String.class))).willReturn(resource);

        // execute and assert
        MvcResult result = mvc.perform(get("/api/file/photo/photo.png"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Disposition"))
                .andReturn();

        assertEquals(100, result.getResponse().getContentLength());
    }
}
