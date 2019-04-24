package com.clickbait.orient.model;

/**
 * Response when uploading a photo
 */
public class Photo {

    // photo's properties
    private String eventId;
    private String teamId;
    private String checkpointId;
    private String downloadURL;
    private String fileType;
    private Long fileSize;

    /**
     * Default no-args constructor
     */
    public Photo() {}

    /**
     * Constructor with arguments
     *
     * @param eventId
     * @param teamId
     * @param checkpointId
     * @param downloadURL
     * @param fileType
     * @param fileSize
     */
    public Photo(String eventId, String teamId, String checkpointId, String downloadURL, String fileType, Long fileSize) {
        this.eventId = eventId;
        this.teamId = teamId;
        this.checkpointId = checkpointId;
        this.downloadURL = downloadURL;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    // GETTERS
    public String getEventId() { return eventId; }
    public String getTeamId() { return teamId; }
    public String getCheckpointId() { return checkpointId; }
    public String getDownloadURL() { return downloadURL; }
    public String getFileType() { return fileType; }
    public Long getFileSize() { return fileSize; }

    // SETTERS
    public void setEventId(String eventId) { this.eventId = eventId; }
    public void setTeamId(String teamId) { this.teamId = teamId; }
    public void setCheckpointId(String checkpointId) { this.checkpointId = checkpointId; }
    public void setDownloadURL(String downloadURL) { this.downloadURL = downloadURL; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
    public void setFileType(String fileType) { this.fileType = fileType; }
}
