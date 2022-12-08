package com.task.domain;

import java.util.List;

import com.task.interfaces.rest.models.EventCreationCommand;
import com.task.interfaces.rest.models.EventUpdateCommand;
import com.task.persistence.entities.Event;

public interface EventService {

    void createEvent(EventCreationCommand event);

    void updateEvent(EventUpdateCommand event);

    void deleteEvent(String eventId);

    Event getEvent(String eventId);

    List<Event> getAllEvents();

    List<Event> getAllEventsByTitle(String title);

}
