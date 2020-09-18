package com.task.infrastructure.messaging.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.task.application.EventProducer;
import com.task.interfaces.rest.EventCreationCommand;
import com.task.interfaces.rest.EventUpdateCommand;

//@Component
//@Profile("rabbitmq")
public class EventProducerToRabbitMQ  implements EventProducer {

//    @RabbitListener
//    @Override
    public void emitCreateEvent(EventCreationCommand event) {

    }

//    @RabbitListener
//    @Override
    public void emitUpdateEvent(EventUpdateCommand event) {

    }

//    @RabbitListener
//    @Override
    public void emitDeleteEvent(String eventId) {

    }

}