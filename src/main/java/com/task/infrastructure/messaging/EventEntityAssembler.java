package com.task.infrastructure.messaging;

import org.springframework.stereotype.Component;

import com.task.interfaces.rest.models.EventCreationCommand;
import com.task.interfaces.rest.models.EventUpdateCommand;
import com.task.persistence.entities.Event;

@Component
public class EventEntityAssembler {

    public Event toEntity(EventCreationCommand eventCreationCommand) {
        var event = new Event();
        event.setTitle(eventCreationCommand.getTitle());
        event.setPlace(eventCreationCommand.getPlace());
        event.setSpeaker(eventCreationCommand.getSpeaker());
        event.setEventType(eventCreationCommand.getEventType());
        event.setDateTime(eventCreationCommand.getDateTime());
        return event;
    }

    public Event toEntity(EventUpdateCommand eventUpdateCommand) {
        var event = new Event();
        event.setId(eventUpdateCommand.getId());
        event.setTitle(eventUpdateCommand.getTitle());
        event.setPlace(eventUpdateCommand.getPlace());
        event.setSpeaker(eventUpdateCommand.getSpeaker());
        event.setEventType(eventUpdateCommand.getEventType());
        event.setDateTime(eventUpdateCommand.getDateTime());
        return event;
    }

}
