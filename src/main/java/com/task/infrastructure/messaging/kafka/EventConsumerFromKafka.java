package com.task.infrastructure.messaging.kafka;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.task.application.EventConsumer;
import com.task.domain.EventRepository;
import com.task.infrastructure.messaging.EventEntityAssembler;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;

import reactor.core.scheduler.Schedulers;

@Component
@Profile("kafka")
public class EventConsumerFromKafka implements EventConsumer {

    private EventRepository eventRepository;

    private EventEntityAssembler eventEntityAssembler;

    public EventConsumerFromKafka(EventRepository eventRepository, EventEntityAssembler eventEntityAssembler) {
        this.eventRepository = eventRepository;
        this.eventEntityAssembler = eventEntityAssembler;
    }

    @KafkaListener(topics = "${events_to_be_created}", groupId = "${mgmt_group.events_to_be_created}")
    @Transactional
    @Override
    public void onCreateEvent(EventCreationCommand eventCreationCommand) {
        eventRepository.save(eventEntityAssembler.toEntity(eventCreationCommand))
                       .subscribeOn(Schedulers.single())
                       .subscribe();
    }

    @KafkaListener(topics = "${events_to_be_updated}", groupId = "${mgmt_group.events_to_be_updated}")
    @Transactional
    @Override
    public void onUpdateEvent(EventUpdateCommand eventUpdateCommand) {
        eventRepository.save(eventEntityAssembler.toEntity(eventUpdateCommand))
                       .subscribeOn(Schedulers.single())
                       .subscribe();
    }

    @KafkaListener(topics = "${events_to_be_deleted}", groupId = "${mgmt_group.events_to_be_deleted}")
    @Transactional
    @Override
    public void onDeleteEvent(String eventId) {
        eventRepository.deleteById(eventId)
                       .subscribeOn(Schedulers.single())
                       .subscribe();
    }

}
