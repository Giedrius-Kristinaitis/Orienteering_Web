package com.clickbait.orient.model;

/**
 * Response when uploading a photo
 */
public class PhotoResponse {

    // photo's properties
    private String downloadURL;
    private String fileType;
    private Long fileSize;

    /**
     * Default no-args constructor
     */
    public PhotoResponse() {}

    /**
     * Constructor with arguments
     *
     * @param downloadURL
     * @param fileType
     * @param fileSize
     */
    public PhotoResponse(String downloadURL, String fileType, Long fileSize) {
        this.downloadURL = downloadURL;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    // GETTERS
    public String getDownloadURL() { return downloadURL; }
    public String getFileType() { return fileType; }
    public Long getFileSize() { return fileSize; }

    // SETTERS
    public void setDownloadURL(String downloadURL) { this.downloadURL = downloadURL; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
    public void setFileType(String fileType) { this.fileType = fileType; }
}
