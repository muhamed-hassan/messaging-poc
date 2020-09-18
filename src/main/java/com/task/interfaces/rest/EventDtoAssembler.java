package com.task.interfaces.rest;

import org.springframework.stereotype.Component;

import com.task.domain.Event;

@Component
public class EventDtoAssembler {

    public EventDTO toDto(Event event) {
        return new EventDTO(event.getId(),
                                event.getTitle(),
                                event.getPlace(),
                                event.getSpeaker(),
                                event.getEventType(),
                                event.getDateTime());
    }

}
