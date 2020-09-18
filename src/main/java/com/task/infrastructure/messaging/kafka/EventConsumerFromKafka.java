package com.task.infrastructure.messaging.kafka;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.task.application.EventConsumer;
import com.task.domain.EventRepository;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;
//@Component
//@Profile("kafka")
public class EventConsumerFromKafka implements EventConsumer {

    private EventRepository eventRepository;

    public EventConsumerFromKafka(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

//    @KafkaListener
//    @Override
    public void onCreateEvent(EventCreationCommand event) {
        
    }

//    @KafkaListener
//    @Override
    public void onUpdateEvent(EventUpdateCommand event) {

    }

//    @KafkaListener
//    @Override
    public void onDeleteEvent(String eventId) {

    }
}
