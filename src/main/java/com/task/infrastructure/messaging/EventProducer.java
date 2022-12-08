package com.task.infrastructure.messaging;

import com.task.interfaces.rest.models.EventCreationCommand;
import com.task.interfaces.rest.models.EventUpdateCommand;

public interface EventProducer {

    void emitCreateEvent(EventCreationCommand event);

    void emitUpdateEvent(EventUpdateCommand event);

    void emitDeleteEvent(String eventId);

}
