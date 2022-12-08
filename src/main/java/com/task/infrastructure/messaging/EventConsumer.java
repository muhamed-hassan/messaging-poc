package com.task.infrastructure.messaging;

import com.task.interfaces.rest.models.EventCreationCommand;
import com.task.interfaces.rest.models.EventUpdateCommand;

public interface EventConsumer {

    void onCreateEvent(EventCreationCommand event);

    void onUpdateEvent(EventUpdateCommand event);

    void onDeleteEvent(String eventId);

}
