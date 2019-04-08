package com.clickbait.orient.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * File service abstraction
 */
@Service
public interface FileService {

    /**
     * Saves a multipart photo file to the specified directory
     *
     * @param file file to save
     * @param eventId to which event the photo belongs
     *
     * @return file download url
     */
    String savePhoto(MultipartFile file, String eventId);

    /**
     * Loads a photo with the given name
     *
     * @param fileName name of the file
     * @return file as resource object
     */
    Resource getPhoto(String fileName);
}
