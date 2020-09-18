package com.task.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.task.domain.Event;
import com.task.domain.EventRepository;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    private EventProducer eventProducer;

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
                                .orElseThrow(EntityNotFoundException::new);
    }

    // TODO: handle data not found using optional
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // TODO: handle data not found using optional
    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return eventRepository.findByTitle(title);
    }

}
