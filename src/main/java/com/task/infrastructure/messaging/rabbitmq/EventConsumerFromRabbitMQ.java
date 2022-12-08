package com.task.infrastructure.messaging.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.task.infrastructure.messaging.EventConsumer;
import com.task.infrastructure.messaging.EventEntityAssembler;
import com.task.interfaces.rest.models.EventCreationCommand;
import com.task.interfaces.rest.models.EventUpdateCommand;
import com.task.persistence.repositories.EventRepository;

@Component
@Profile("rabbitmq")
public class EventConsumerFromRabbitMQ implements EventConsumer {

    private final EventRepository eventRepository;

    private final EventEntityAssembler eventEntityAssembler;

    public EventConsumerFromRabbitMQ(EventRepository eventRepository, EventEntityAssembler eventEntityAssembler) {
        this.eventRepository = eventRepository;
        this.eventEntityAssembler = eventEntityAssembler;
    }

    @RabbitListener(queues = "${events_to_be_created}")
    @Transactional
    @Override
    public void onCreateEvent(EventCreationCommand eventCreationCommand) {
        eventRepository.save(eventEntityAssembler.toEntity(eventCreationCommand));
    }

    @RabbitListener(queues = "${events_to_be_updated}")
    @Transactional
    @Override
    public void onUpdateEvent(EventUpdateCommand eventUpdateCommand) {
        eventRepository.save(eventEntityAssembler.toEntity(eventUpdateCommand));
    }

    @RabbitListener(queues = "${events_to_be_deleted}")
    @Transactional
    @Override
    public void onDeleteEvent(String eventId) {
        eventRepository.deleteById(eventId);
    }

}
