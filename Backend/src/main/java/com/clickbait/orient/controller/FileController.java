package com.clickbait.orient.controller;

import com.clickbait.orient.model.PhotoResponse;
import com.clickbait.orient.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Handles requests related to files
 */
@RestController
@RequestMapping("/api/file")
public class FileController {

    // service used to manipulate files
    private FileService service;

    /**
     * Class constructor
     * @param service injected service
     */
    @Autowired
    public FileController(FileService service) {
        this.service = service;
    }

    /**
     * Uploads a photo
     * @return information about the uploaded photo
     */
    @PostMapping("/photo/{eventId}")
    public ResponseEntity<PhotoResponse> uploadPhoto(@RequestParam("file") MultipartFile file, @PathVariable String eventId) {
        String downloadURL = service.savePhoto(file);


        if (downloadURL == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        PhotoResponse response = new PhotoResponse(downloadURL, file.getContentType(), file.getSize());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Downloads a photo
     *
     * @return response with the photo resource
     */
    @GetMapping("/photo/{fileName}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = service.getPhoto(fileName);

        if (resource == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // get the correct mime type
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {}

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        // return the response
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
