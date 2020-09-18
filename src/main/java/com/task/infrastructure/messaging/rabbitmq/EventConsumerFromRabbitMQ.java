package com.task.infrastructure.messaging.rabbitmq;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.task.application.EventConsumer;
import com.task.domain.Event;
import com.task.domain.EventRepository;
import com.task.infrastructure.messaging.EventEntityAssembler;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;

//@Component
//@Profile("rabbitmq")
public class EventConsumerFromRabbitMQ implements EventConsumer {

    private EventRepository eventRepository;

    private EventEntityAssembler eventEntityAssembler;

    public EventConsumerFromRabbitMQ(EventRepository eventRepository, EventEntityAssembler eventEntityAssembler) {
        this.eventRepository = eventRepository;
        this.eventEntityAssembler = eventEntityAssembler;
    }

//    @RabbitListener
//    @Transactional
//    @Override
    public void onCreateEvent(EventCreationCommand eventCreationCommand) {
        eventRepository.save(eventEntityAssembler.toEntity(eventCreationCommand));
    }

//    @RabbitListener
//    @Transactional
//    @Override
    public void onUpdateEvent(EventUpdateCommand eventUpdateCommand) {
        eventRepository.save(eventEntityAssembler.toEntity(eventUpdateCommand));
    }

//    @RabbitListener
//    @Transactional
//    @Override
    public void onDeleteEvent(String eventId) {
        eventRepository.deleteById(eventId);
    }

}
