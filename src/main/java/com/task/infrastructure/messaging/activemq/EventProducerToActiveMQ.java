package com.task.infrastructure.messaging.activemq;

import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.task.application.EventProducer;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;

@Component
@Profile("activemq")
public class EventProducerToActiveMQ implements EventProducer {

    private JmsTemplate jmsTemplate;

    public EventProducerToActiveMQ(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void emitCreateEvent(EventCreationCommand eventCreationCommand) {
        jmsTemplate.convertAndSend("${queues.events_to_be_created}", eventCreationCommand);
    }

    @Override
    public void emitUpdateEvent(EventUpdateCommand eventUpdateCommand) {
        jmsTemplate.convertAndSend("${queues.events_to_be_updated}", eventUpdateCommand);
    }

    @Override
    public void emitDeleteEvent(String eventId) {
        jmsTemplate.convertAndSend("${queues.events_to_be_deleted}", eventId);
    }

}
