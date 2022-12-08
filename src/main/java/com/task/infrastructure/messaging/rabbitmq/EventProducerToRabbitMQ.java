package com.task.infrastructure.messaging.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.task.infrastructure.messaging.EventProducer;
import com.task.interfaces.rest.models.EventCreationCommand;
import com.task.interfaces.rest.models.EventUpdateCommand;

@Component
@Profile("rabbitmq")
public class EventProducerToRabbitMQ implements EventProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${events_to_be_created}")
    private String creatingQueue;

    @Value("${events_to_be_updated}")
    private String updatingQueue;

    @Value("${events_to_be_deleted}")
    private String deletingQueue;

    public EventProducerToRabbitMQ(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void emitCreateEvent(EventCreationCommand eventCreationCommand) {
        rabbitTemplate.convertAndSend(creatingQueue, eventCreationCommand);
    }

    @Override
    public void emitUpdateEvent(EventUpdateCommand eventUpdateCommand) {
        rabbitTemplate.convertAndSend(updatingQueue, eventUpdateCommand);
    }

    @Override
    public void emitDeleteEvent(String eventId) {
        rabbitTemplate.convertAndSend(deletingQueue, eventId);
    }

}