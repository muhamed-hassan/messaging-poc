package com.task.interfaces.rest.models;

import org.springframework.stereotype.Component;

import com.task.persistence.entities.Event;

@Component
public class EventDtoAssembler {

    public EventDTO toDto(Event event) {
    	var eventDTO = new EventDTO();
    	eventDTO.setId(event.getId());
    	eventDTO.setTitle(event.getTitle());
    	eventDTO.setPlace(event.getPlace());
    	eventDTO.setSpeaker(event.getSpeaker());
    	eventDTO.setEventType(event.getEventType());
    	eventDTO.setDateTime(event.getDateTime());
        return eventDTO;
    }

}
