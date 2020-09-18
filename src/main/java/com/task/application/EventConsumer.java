package com.task.application;

import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;

public interface EventConsumer {

    void onCreateEvent(EventCreationCommand event);

    void onUpdateEvent(EventUpdateCommand event);

    void onDeleteEvent(String eventId);

}
