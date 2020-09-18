package com.task.interfaces.rest;

public class EventUpdateCommand extends BaseEventCommand {

    private String id;

    public EventUpdateCommand() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
