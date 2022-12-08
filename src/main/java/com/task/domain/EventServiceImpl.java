package com.task.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.task.infrastructure.messaging.EventProducer;
import com.task.interfaces.rest.models.EventCreationCommand;
import com.task.interfaces.rest.models.EventUpdateCommand;
import com.task.persistence.entities.Event;
import com.task.persistence.repositories.EventRepository;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final EventProducer eventProducer;

    public EventServiceImpl(EventRepository eventRepository, EventProducer eventProducer) {
        this.eventRepository = eventRepository;
        this.eventProducer = eventProducer;
    }

    @Override
    public void createEvent(EventCreationCommand event) {
        eventProducer.emitCreateEvent(event);
    }

    @Override
    public void updateEvent(EventUpdateCommand event) {
        eventProducer.emitUpdateEvent(event);
    }

    @Override
    public void deleteEvent(String eventId) {
        eventProducer.emitDeleteEvent(eventId);
    }

    @Override
    public Event getEvent(String eventId) {    	
    	return eventRepository.findById(eventId)
    			.orElseThrow(() -> new RuntimeException("This event with id " + eventId + " does not exist"));
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return eventRepository.findByTitle(title);
    }

}
