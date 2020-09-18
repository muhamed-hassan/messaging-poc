package com.task.infrastructure.messaging.activemq;

import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.task.application.EventConsumer;
import com.task.domain.EventRepository;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;

@Component
@Profile("activemq")
public class EventConsumerFromActiveMQ implements EventConsumer {

    private EventRepository eventRepository;

    public EventConsumerFromActiveMQ(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @JmsListener(destination = "")
    @Override
    public void onCreateEvent(EventCreationCommand event) {
        
    }

    @JmsListener(destination = "")
    @Override
    public void onUpdateEvent(EventUpdateCommand event) {

    }

    @JmsListener(destination = "")
    @Override
    public void onDeleteEvent(String eventId) {

    }
}
