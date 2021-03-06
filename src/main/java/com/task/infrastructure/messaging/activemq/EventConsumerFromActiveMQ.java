package com.task.infrastructure.messaging.activemq;

import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.task.application.EventConsumer;
import com.task.domain.EventRepository;
import com.task.infrastructure.messaging.EventEntityAssembler;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;

import reactor.core.scheduler.Schedulers;

@Component
@Profile("activemq")
public class EventConsumerFromActiveMQ implements EventConsumer {

    private final EventRepository eventRepository;

    private final EventEntityAssembler eventEntityAssembler;

    public EventConsumerFromActiveMQ(EventRepository eventRepository, EventEntityAssembler eventEntityAssembler) {
        this.eventRepository = eventRepository;
        this.eventEntityAssembler = eventEntityAssembler;
    }

    @JmsListener(destination = "${events_to_be_created}", containerFactory = "jmsListenerContainerFactory")
    @Override
    public void onCreateEvent(EventCreationCommand eventCreationCommand) {
        eventRepository.save(eventEntityAssembler.toEntity(eventCreationCommand))
                       .subscribeOn(Schedulers.single())
                       .subscribe();
    }

    @JmsListener(destination = "${events_to_be_updated}", containerFactory = "jmsListenerContainerFactory")
    @Override
    public void onUpdateEvent(EventUpdateCommand eventUpdateCommand) {
        eventRepository.save(eventEntityAssembler.toEntity(eventUpdateCommand))
                       .subscribeOn(Schedulers.single())
                       .subscribe();
    }

    @JmsListener(destination = "${events_to_be_deleted}", containerFactory = "jmsListenerContainerFactory")
    @Override
    public void onDeleteEvent(String eventId) {
        eventRepository.deleteById(eventId)
                       .subscribeOn(Schedulers.single())
                       .subscribe();
    }

}
