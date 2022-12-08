package com.task.interfaces.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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

@RequestMapping("events")
@RestController
@Validated
public class EventController {

    private final EventService eventService;

    private final EventDtoAssembler eventDtoAssembler;

    public EventController(EventService eventService, EventDtoAssembler eventDtoAssembler) {
        this.eventService = eventService;
        this.eventDtoAssembler = eventDtoAssembler;
    }

    @PostMapping
    public ResponseEntity<Void> createEvent(@RequestBody @Valid EventCreationCommand eventCreationCommand) {
        eventService.createEvent(eventCreationCommand);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateEvent(@RequestBody @Valid EventUpdateCommand eventUpdateCommand) {
        eventService.updateEvent(eventUpdateCommand);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable @NotBlank(message = "event id is required") String eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("{eventId}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable @NotBlank(message = "event id is required") String eventId) {
    	var response = eventDtoAssembler.toDto(eventService.getEvent(eventId));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
    	var response = eventService.getAllEvents()
    			.stream()
    			.map(eventDtoAssembler::toDto)
    			.collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "search", params = "title")
    public ResponseEntity<List<EventDTO>> getAllEventsByTitle(@RequestParam @NotBlank(message = "title is required") String title) {
    	var response = eventService.getAllEventsByTitle(title)
    			.stream()
    			.map(eventDtoAssembler::toDto)
    			.collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

}
