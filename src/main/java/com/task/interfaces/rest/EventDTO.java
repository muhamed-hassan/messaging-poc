package com.task.interfaces.rest;

import java.time.LocalDateTime;

public class EventDTO {

    private final String id;

    private final String title;

    private final String place;

    private final String speaker;

    private final String eventType;

    private final LocalDateTime dateTime;

    public EventDTO(String id, String title, String place, String speaker, String eventType, LocalDateTime dateTime) {
        this.id = id;
        this.title = title;
        this.place = place;
        this.speaker = speaker;
        this.eventType = eventType;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPlace() {
        return place;
    }

    public String getSpeaker() {
        return speaker;
    }

    public String getEventType() {
        return eventType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
