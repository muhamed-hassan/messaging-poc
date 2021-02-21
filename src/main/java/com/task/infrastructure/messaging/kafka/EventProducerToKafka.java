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

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${events_to_be_created}")
    private String creatingTopic;

    @Value("${events_to_be_updated}")
    private String updatingTopic;

    @Value("${events_to_be_deleted}")
    private String deletingTopic;

    public EventProducerToKafka(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void emitCreateEvent(EventCreationCommand eventCreationCommand) {
        kafkaTemplate.send(creatingTopic, eventCreationCommand);
    }

    @Override
    public void emitUpdateEvent(EventUpdateCommand eventUpdateCommand) {
        kafkaTemplate.send(updatingTopic, eventUpdateCommand);
    }

    @Override
    public void emitDeleteEvent(String eventId) {
        kafkaTemplate.send(deletingTopic, eventId);
    }

}
