package com.riwi.eventsvenues.controller;

import com.riwi.eventsvenues.dto.EventRequest;
import com.riwi.eventsvenues.dto.EventResponse;
import com.riwi.eventsvenues.service.impl.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@Tag(name = "Events", description = "API for event management")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "List all events",
            description = "Get a list of all available events")
    @ApiResponse(responseCode = "200", description = "List of events successfully retrieved")
    @GetMapping
    public ResponseEntity<List<EventResponse>> findAll() {
        List<EventResponse> events = eventService.findAll();
        return ResponseEntity.ok().body(events);
    }

    @Operation(summary = "Find event by ID",
            description = "Get a specific event by its ID")
    @ApiResponse(responseCode = "200", description = "Event found")
    @ApiResponse(responseCode = "404", description = "Event not found")
    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> findById(
            @Parameter(description = "ID of the event to search for")
            @Valid @PathVariable Long id) {
        EventResponse event = eventService.findById(id);
        return ResponseEntity.ok().body(event);
    }

    @Operation(summary = "Create new event",
            description = "Create a new event with the provided data")
    @ApiResponse(responseCode = "201", description = "Event created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid event data")
    @PostMapping
    public ResponseEntity<EventResponse> save(
            @Parameter(description = "Data of the event to create")
            @Valid @RequestBody EventRequest request) {
        EventResponse savedEvent = eventService.create(request);
        return ResponseEntity.status(201).body(savedEvent);
    }

    @Operation(summary = "Update event",
            description = "Update an existing event by its ID")
    @ApiResponse(responseCode = "200", description = "Event updated successfully")
    @ApiResponse(responseCode = "404", description = "Event not found")
    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> update(
            @Parameter(description = "ID of the event to update")
            @Valid @PathVariable Long id,
            @Parameter(description = "New data for the event")
            @Valid @RequestBody EventRequest request) {
        EventResponse updatedEvent = eventService.update(id, request);
        return ResponseEntity.ok().body(updatedEvent);
    }

    @Operation(summary = "Delete event",
            description = "Delete an existing event by its ID")
    @ApiResponse(responseCode = "204", description = "Event deleted successfully")
    @ApiResponse(responseCode = "404", description = "Event not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the event to delete")
            @Valid @PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
