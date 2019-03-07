package com.clickbait.orient.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * All statuses that an event can have
 */
public enum EventStatus {

    OPEN("Open"),
    IN_PROGRESS("In progress"),
    CLOSED("Closed");

    private String status;

    /**
     * Enum constructor. Can only be private (no need to specify)
     * @param status
     */
    EventStatus(String status) {
        this.status = status;
    }

    /**
     * Converts the status to a string
     * @return
     */
    @JsonValue
    @Override
    public String toString() {
        return status;
    }
}
