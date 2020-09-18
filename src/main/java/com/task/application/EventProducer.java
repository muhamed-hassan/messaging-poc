package com.task.application;

import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;

public interface EventProducer {

    void emitCreateEvent(EventCreationCommand event);

    void emitUpdateEvent(EventUpdateCommand event);

    void emitDeleteEvent(String eventId);

}
