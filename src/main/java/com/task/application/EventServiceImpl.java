package com.task.application;

import org.springframework.stereotype.Service;

import com.task.domain.Event;
import com.task.domain.EventRepository;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<Event> getEvent(String eventId) {
        return eventRepository.findById(eventId)
                              .switchIfEmpty(Mono.error(new EntityNotFoundException()));
    }

    @Override
    public Flux<Event> getAllEvents() {
        return eventRepository.findAll()
                              .switchIfEmpty(Mono.error(new NoResultException()));
    }

    @Override
    public Flux<Event> getAllEventsByTitle(String title) {
        return  eventRepository.findByTitle(title)
                               .switchIfEmpty(Mono.error(new NoResultException()));
    }

}
