package com.task.interfaces.rest;

import java.time.LocalDateTime;

public class EventDTO {

    private final String id;

    private final String title;

    private final String place;

    private final String speaker;

    private final String eventType;

    private final LocalDateTime dateTime;

    private EventDTO(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.place = builder.place;
        this.speaker = builder.speaker;
        this.eventType = builder.eventType;
        this.dateTime = builder.dateTime;
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

    public static class Builder {

        private String id;

        private String title;

        private String place;

        private String speaker;

        private String eventType;

        private LocalDateTime dateTime;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder place(String place) {
            this.place = place;
            return this;
        }

        public Builder speaker(String speaker) {
            this.speaker = speaker;
            return this;
        }
        
        public Builder eventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder dateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }
        
        public EventDTO build() {
            return new EventDTO(this);
        }
        
    }

}
