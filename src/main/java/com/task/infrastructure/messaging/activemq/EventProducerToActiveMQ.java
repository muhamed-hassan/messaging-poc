package com.task.infrastructure.messaging.activemq;

import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.task.application.EventProducer;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;
@Component
@Profile("activemq")
public class EventProducerToActiveMQ implements EventProducer {

    @JmsListener(destination = "")
    @Override
    public void emitCreateEvent(EventCreationCommand event) {
        
    }

    @JmsListener(destination = "")
    @Override
    public void emitUpdateEvent(EventUpdateCommand event) {

    }

    @JmsListener(destination = "")
    @Override
    public void emitDeleteEvent(String eventId) {

    }
}
