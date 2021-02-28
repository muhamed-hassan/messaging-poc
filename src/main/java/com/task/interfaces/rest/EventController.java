package com.task.interfaces.rest;

import java.net.HttpURLConnection;

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

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @ApiResponses(value = {
        @ApiResponse(code = HttpURLConnection.HTTP_ACCEPTED, message = "Succeeded in publishing new event for creation"),
        @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Invalid payload"),
        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Void> createEvent(@RequestBody @Valid EventCreationCommand eventCreationCommand) {
        eventService.createEvent(eventCreationCommand);
        return ResponseEntity.accepted().build();
    }

    @ApiResponses(value = {
        @ApiResponse(code = HttpURLConnection.HTTP_ACCEPTED, message = "Succeeded in publishing a modified event to be updated"),
        @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Invalid payload"),
        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error")
    })
    @PatchMapping
    public ResponseEntity<Void> updateEvent(@RequestBody @Valid EventUpdateCommand eventUpdateCommand) {
        eventService.updateEvent(eventUpdateCommand);
        return ResponseEntity.accepted().build();
    }

    @ApiResponses(value = {
        @ApiResponse(code = HttpURLConnection.HTTP_ACCEPTED, message = "Succeeded in publishing an event id for deletion"),
        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error")
    })
    @DeleteMapping("{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable @NotBlank(message = "event id is required") String eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.accepted().build();
    }

    @ApiResponses(value = {
        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Succeeded in fetching an event"),
        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Data not found"),
        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error")
    })
    @GetMapping("{eventId}")
    public ResponseEntity<Mono<EventDTO>> getEvent(@PathVariable @NotBlank(message = "event id is required") String eventId) {
        return ResponseEntity.ok(eventService.getEvent(eventId).map(eventDtoAssembler::toDto));
    }

    @ApiResponses(value = {
        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Succeeded in fetching events"),
        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Data not found"),
        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<Flux<EventDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents().map(eventDtoAssembler::toDto));
    }

    @ApiResponses(value = {
        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Succeeded in fetching events"),
        @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Data not found"),
        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error")
    })
    @GetMapping(path = "search", params = "title")
    public ResponseEntity<Flux<EventDTO>> getAllEventsByTitle(@RequestParam @NotBlank(message = "title is required") String title) {
        return ResponseEntity.ok(eventService.getAllEventsByTitle(title).map(eventDtoAssembler::toDto));
    }

}
