package com.clickbait.orient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Holds configuration properties for file management
 */
@ConfigurationProperties(prefix = "file")
public class FileConfig {

    // PROPERTIES
    private String photoUploadDir;

    // SETTERS
    public void setPhotoUploadDir(String photoUploadDir) { this.photoUploadDir = photoUploadDir; }

    // GETTERS
    public String getPhotoUploadDir() { return photoUploadDir; }
}
