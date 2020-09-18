package com.task.infrastructure.messaging.kafka;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.task.application.EventProducer;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;
@Component
@Profile("kafka")
public class EventProducerToKafka  implements EventProducer {
    @Override
    public void emitCreateEvent(EventCreationCommand event) {

    }

    @Override
    public void emitUpdateEvent(EventUpdateCommand event) {

    }

    @Override
    public void emitDeleteEvent(String eventId) {

    }
}
