package com.task.infrastructure.messaging.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.task.application.EventConsumer;
import com.task.domain.EventRepository;
import com.task.infrastructure.messaging.EventEntityAssembler;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;

import reactor.core.scheduler.Schedulers;

@Component
@Profile("rabbitmq")
public class EventConsumerFromRabbitMQ implements EventConsumer {

    private EventRepository eventRepository;

    private EventEntityAssembler eventEntityAssembler;

    public EventConsumerFromRabbitMQ(EventRepository eventRepository, EventEntityAssembler eventEntityAssembler) {
        this.eventRepository = eventRepository;
        this.eventEntityAssembler = eventEntityAssembler;
    }

    @RabbitListener(queues = "${events_to_be_created}")
    @Transactional
    @Override
    public void onCreateEvent(EventCreationCommand eventCreationCommand) {
        eventRepository.save(eventEntityAssembler.toEntity(eventCreationCommand))
                       .subscribeOn(Schedulers.single())
                       .subscribe();
    }

    @RabbitListener(queues = "${events_to_be_updated}")
    @Transactional
    @Override
    public void onUpdateEvent(EventUpdateCommand eventUpdateCommand) {
        eventRepository.save(eventEntityAssembler.toEntity(eventUpdateCommand))
                       .subscribeOn(Schedulers.single())
                       .subscribe();
    }

    @RabbitListener(queues = "${events_to_be_deleted}")
    @Transactional
    @Override
    public void onDeleteEvent(String eventId) {
        eventRepository.deleteById(eventId)
                       .subscribeOn(Schedulers.single())
                       .subscribe();
    }

}
