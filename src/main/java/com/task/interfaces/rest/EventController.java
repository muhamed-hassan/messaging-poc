package com.task.interfaces.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.application.EventService;
import com.task.domain.Event;

@RequestMapping("events")
@RestController
@Validated
public class EventController {

    private EventService eventService;

    private EventDtoAssembler eventDtoAssembler;

    public EventController(EventService eventService, EventDtoAssembler eventDtoAssembler) {
        this.eventService = eventService;
        this.eventDtoAssembler = eventDtoAssembler;
    }

    @PostMapping
    public ResponseEntity<Void> createEvent(/*@Valid*/ @RequestBody EventCreationCommand eventCreationCommand) {
        eventService.createEvent(eventCreationCommand);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateEvent(/*@Valid*/ @RequestBody EventUpdateCommand eventUpdateCommand) {
        eventService.updateEvent(eventUpdateCommand);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<EventDTO> getEvent(String eventId) {
        return ResponseEntity.ok(eventDtoAssembler.toDto(eventService.getEvent(eventId)));
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents()
            .stream().map(eventDtoAssembler::toDto).collect(Collectors.toList()));
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEventsByTitle(@RequestParam String title) {
        return ResponseEntity.ok(eventService.getAllEventsByTitle(title)
            .stream().map(eventDtoAssembler::toDto).collect(Collectors.toList()));
    }

}
