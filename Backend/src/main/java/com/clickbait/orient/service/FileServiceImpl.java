package com.clickbait.orient.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles files
 */
public class FileServiceImpl implements FileService {

    /**
     * Saves a multipart file to the specified directory
     *
     * @param file file to save
     * @param dir  name of the directory
     */
    @Override
    public void saveFile(MultipartFile file, String dir) {

    }

    /**
     * Loads a file with the given name
     *
     * @param fileName name of the file
     * @return file as resource object
     */
    @Override
    public Resource getFile(String fileName) {

        return null;
    }
}
