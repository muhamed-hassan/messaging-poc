package com.task.infrastructure.messaging.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.task.application.EventProducer;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;

@Component
@Profile("rabbitmq")
public class EventProducerToRabbitMQ implements EventProducer {

    private RabbitTemplate rabbitTemplate;

    @Value("${queues.events_to_be_created}")
    private String creatingQueue;

    @Value("${queues.events_to_be_updated}")
    private String updatingQueue;

    @Value("${queues.events_to_be_deleted}")
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