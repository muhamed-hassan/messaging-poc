package com.task.interfaces.rest;

import org.springframework.stereotype.Component;

import com.task.domain.Event;

@Component
public class EventDtoAssembler {

    public EventDTO toDto(Event event) {
        return new EventDTO.Builder()
                                .id(event.getId())
                                .title(event.getTitle())
                                .place(event.getPlace())
                                .speaker(event.getSpeaker())
                                .eventType(event.getEventType())
                                .dateTime(event.getDateTime())
                            .build();
    }

}
