package com.task.infrastructure.messaging.kafka;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.task.infrastructure.messaging.EventConsumer;
import com.task.infrastructure.messaging.EventEntityAssembler;
import com.task.interfaces.rest.models.EventCreationCommand;
import com.task.interfaces.rest.models.EventUpdateCommand;
import com.task.persistence.repositories.EventRepository;

@Component
@Profile("kafka")
public class EventConsumerFromKafka implements EventConsumer {

    private final EventRepository eventRepository;

    private final EventEntityAssembler eventEntityAssembler;

    public EventConsumerFromKafka(EventRepository eventRepository, EventEntityAssembler eventEntityAssembler) {
        this.eventRepository = eventRepository;
        this.eventEntityAssembler = eventEntityAssembler;
    }

    @KafkaListener(topics = "${events_to_be_created}", groupId = "${mgmt_group.events_to_be_created}")
    @Transactional
    @Override
    public void onCreateEvent(EventCreationCommand eventCreationCommand) {
        eventRepository.save(eventEntityAssembler.toEntity(eventCreationCommand));
    }

    @KafkaListener(topics = "${events_to_be_updated}", groupId = "${mgmt_group.events_to_be_updated}")
    @Transactional
    @Override
    public void onUpdateEvent(EventUpdateCommand eventUpdateCommand) {
        eventRepository.save(eventEntityAssembler.toEntity(eventUpdateCommand));
    }

    @KafkaListener(topics = "${events_to_be_deleted}", groupId = "${mgmt_group.events_to_be_deleted}")
    @Transactional
    @Override
    public void onDeleteEvent(String eventId) {
        eventRepository.deleteById(eventId);
    }

}
