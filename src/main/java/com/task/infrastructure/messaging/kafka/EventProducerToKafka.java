package com.task.infrastructure.messaging.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.task.application.EventProducer;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;

@Component
@Profile("kafka")
public class EventProducerToKafka implements EventProducer {

    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${queues.events_to_be_created}")
    private String creatingQueue;

    @Value("${queues.events_to_be_updated}")
    private String updatingQueue;

    @Value("${queues.events_to_be_deleted}")
    private String deletingQueue;

    public EventProducerToKafka(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void emitCreateEvent(EventCreationCommand eventCreationCommand) {
        kafkaTemplate.send(creatingQueue, eventCreationCommand);
    }

    @Override
    public void emitUpdateEvent(EventUpdateCommand eventUpdateCommand) {
        kafkaTemplate.send(updatingQueue, eventUpdateCommand);
    }

    @Override
    public void emitDeleteEvent(String eventId) {
        kafkaTemplate.send(deletingQueue, eventId);
    }

}
