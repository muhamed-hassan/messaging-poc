package com.task.interfaces.rest;

import java.time.LocalDateTime;

public class EventDTO {

    private String id;

    private String title;

    private String place;

    private String speaker;

    private String eventType;

    private LocalDateTime dateTime;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(final String place) {
        this.place = place;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(final String speaker) {
        this.speaker = speaker;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(final String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(final LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
