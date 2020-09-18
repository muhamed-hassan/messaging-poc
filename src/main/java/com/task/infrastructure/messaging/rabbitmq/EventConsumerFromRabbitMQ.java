package com.task.infrastructure.messaging.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.task.application.EventConsumer;
import com.task.domain.EventRepository;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;
@Component
@Profile("rabbitmq")
public class EventConsumerFromRabbitMQ implements EventConsumer  {

    private EventRepository eventRepository;

    public EventConsumerFromRabbitMQ(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @RabbitListener
    @Override
    public void onCreateEvent(EventCreationCommand event) {
        
    }

    @RabbitListener
    @Override
    public void onUpdateEvent(EventUpdateCommand event) {

    }

    @RabbitListener
    @Override
    public void onDeleteEvent(String eventId) {

    }
}
