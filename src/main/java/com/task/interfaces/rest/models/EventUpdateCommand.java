package com.task.interfaces.rest.models;

import javax.validation.constraints.NotBlank;

public class EventUpdateCommand extends BaseEventCommand {

    @NotBlank(message = "id is required")
    private String id;

    public EventUpdateCommand() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
