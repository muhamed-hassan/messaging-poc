package com.task.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("event")
public class Event {

    @Id
    private String id;

    private String title;

    private String place;

    private String speaker;

    private String eventType;

    private LocalDateTime dateTime;

    public Event() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Event))
            return false;
        Event that = (Event) other;
        return Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(place, that.place) &&
            Objects.equals(speaker, that.speaker) &&
            Objects.equals(eventType, that.eventType) &&
            Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, place, speaker, eventType, dateTime);
    }

}
