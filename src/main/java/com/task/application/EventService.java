package com.task.application;

import java.util.List;

import com.task.domain.Event;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;

public interface EventService {

    void createEvent(EventCreationCommand event);

    void updateEvent(EventUpdateCommand event);

    void deleteEvent(String eventId);

    Event getEvent(String eventId);

    List<Event> getAllEvents();

    List<Event> getAllEventsByTitle(String title);

}
