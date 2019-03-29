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
     * Saves a multipart file to the specified directory
     *
     * @param file file to save
     * @param dir name of the directory
     */
    void saveFile(MultipartFile file, String dir);

    /**
     * Loads a file with the given name
     *
     * @param fileName name of the file
     * @return file as resource object
     */
    Resource getFile(String fileName);
}
