package com.task.interfaces.rest;

import java.time.LocalDateTime;

public class EventUpdateCommand extends BaseEventCommand {

    private String id;
    EventUpdateCommand() {}
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }
}
