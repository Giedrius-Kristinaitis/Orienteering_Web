package com.clickbait.orient.service;

import com.clickbait.orient.config.FileConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Handles files
 */
public class FileServiceImpl implements FileService {

    // file configuration to get properties
    private FileConfig fileConfig;

    /**
     * Class constructor
     * @param fileConfig automatically injected file configuration
     */
    @Autowired
    public FileServiceImpl(FileConfig fileConfig) {
        this.fileConfig = fileConfig;

        // create file upload directory(-ies)
        File photoDir = new File(fileConfig.getPhotoUploadDir());

        if (!photoDir.exists()) {
            photoDir.mkdir();
        }
    }

    /**
     * Saves a multipart photo file to the specified directory
     *
     * @param file file to save
     *
     * @return file download url
     */
    @Override
    public String savePhoto(MultipartFile file) {
        // validate file name
        if (file.getOriginalFilename().matches("[#@$%^&*+]")) {
            return null;
        }

        File photoFile = new File(fileConfig.getPhotoUploadDir() + "/" + System.currentTimeMillis() + "_" + file.getOriginalFilename());

        try {
            if (!photoFile.exists()) {
                photoFile.createNewFile();
            }

            Files.copy(file.getInputStream(), Paths.get(photoFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            return null;
        }

        // return the download url
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/photo/")
                .path(photoFile.getName())
                .toUriString();
    }

    /**
     * Loads a photo file with the given name
     *
     * @param fileName name of the file
     * @return file as resource object
     */
    @Override
    public Resource getPhoto(String fileName) {
        try {
            Resource resource = new UrlResource(Paths.get(fileConfig.getPhotoUploadDir()).resolve(fileName).normalize().toUri());

            if (resource.exists()) {
                return resource;
            }
        } catch (MalformedURLException ex) { }

        return null;
    }
}
