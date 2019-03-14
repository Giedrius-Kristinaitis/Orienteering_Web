package com.clickbait.orient.model;

import java.util.List;

/**
 * What gets returned by /api/event/page/{pageNumber}/{pageSize} endpoint
 */
@SuppressWarnings("unused")
public class EventsResponse {

    private List<Event> events;
    private Long totalElements;
    private Integer pageSize;
    private Integer totalPages;

    /**
     * Default class constructor
     * @param events
     * @param totalElements
     * @param pageSize
     * @param totalPages
     */
    public EventsResponse(List<Event> events, Long totalElements, Integer pageSize, Integer totalPages) {
        this.events = events;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
    }

    // GETTERS
    public List<Event> getEvents() { return events; }
    public Long getTotalElements() { return totalElements; }
    public Integer getPageSize() { return pageSize; }
    public Integer getTotalPages() { return totalPages; }

    // SETTERS
    public void setEvents(List<Event> events) { this.events = events; }
    public void setTotalElements(Long totalElements) { this.totalElements = totalElements; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
    public void setTotalPages(Integer totalPages) { this.totalPages = totalPages; }
}
