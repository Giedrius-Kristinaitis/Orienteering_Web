package com.clickbait.orient.model;

/**
 * Response when uploading a photo
 */
public class PhotoResponse {

    // photo's properties
    private String downloadURL;
    private String fileType;
    private String fileName;
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
     * @param fileName
     * @param fileSize
     */
    public PhotoResponse(String downloadURL, String fileType, String fileName, Long fileSize) {
        this.downloadURL = downloadURL;
        this.fileType = fileType;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    // GETTERS
    public String getDownloadURL() { return downloadURL; }
    public String getFileType() { return fileType; }
    public String getFileName() { return fileName; }
    public Long getFileSize() { return fileSize; }

    // SETTERS
    public void setDownloadURL(String downloadURL) { this.downloadURL = downloadURL; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
    public void setFileType(String fileType) { this.fileType = fileType; }
}
