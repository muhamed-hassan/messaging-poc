package com.task.application;

import com.task.domain.Event;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventService {

    void createEvent(EventCreationCommand event);

    void updateEvent(EventUpdateCommand event);

    void deleteEvent(String eventId);

    Mono<Event> getEvent(String eventId);

    Flux<Event> getAllEvents();

    Flux<Event> getAllEventsByTitle(String title);

}
