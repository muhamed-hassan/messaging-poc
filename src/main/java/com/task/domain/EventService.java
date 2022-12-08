package com.task.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.task.infrastructure.messaging.EventProducer;
import com.task.interfaces.rest.models.EventCreationCommand;
import com.task.interfaces.rest.models.EventUpdateCommand;
import com.task.persistence.entities.Event;
import com.task.persistence.repositories.EventRepository;

@Service
public class EventService {

    private final EventRepository eventRepository;

    private final EventProducer eventProducer;

    public EventService(EventRepository eventRepository, EventProducer eventProducer) {
        this.eventRepository = eventRepository;
        this.eventProducer = eventProducer;
    }
    
    public void createEvent(EventCreationCommand event) {
        eventProducer.emitCreateEvent(event);
    }
    
    public void updateEvent(EventUpdateCommand event) {
        eventProducer.emitUpdateEvent(event);
    }
    
    public void deleteEvent(String eventId) {
        eventProducer.emitDeleteEvent(eventId);
    }
    
    public Event getEvent(String eventId) {    	
    	return eventRepository.findById(eventId)
    			.orElseThrow(() -> new RuntimeException("This event with id " + eventId + " does not exist"));
    }
    
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    
    public List<Event> getAllEventsByTitle(String title) {
        return eventRepository.findByTitle(title);
    }

}
