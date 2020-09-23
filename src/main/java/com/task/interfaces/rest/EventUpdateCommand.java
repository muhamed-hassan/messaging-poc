package com.task.interfaces.rest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EventUpdateCommand extends BaseEventCommand {

    @NotNull @NotBlank
    private String id;

    public EventUpdateCommand() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
