package com.task.interfaces.rest;

import javax.validation.constraints.NotBlank;

public class EventUpdateCommand extends BaseEventCommand {

    @NotBlank
    private String id;

    public EventUpdateCommand() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
