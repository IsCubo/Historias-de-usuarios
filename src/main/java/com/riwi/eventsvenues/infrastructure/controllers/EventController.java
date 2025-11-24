package com.riwi.eventsvenues.infrastructure.controllers;

import com.riwi.eventsvenues.domain.model.Event;
import com.riwi.eventsvenues.domain.ports.in.event.*;
import com.riwi.eventsvenues.infrastructure.dto.EventRequest;
import com.riwi.eventsvenues.infrastructure.dto.EventResponse;
import com.riwi.eventsvenues.infrastructure.mapper.EventWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/events")
@Tag(name = "Events", description = "Endpoints para la gestión de eventos")
@RequiredArgsConstructor
public class EventController {

    private final CreateEventUseCase createUseCase;
    private final FindEventByIdUseCase findByIdUseCase;
    private final FindAllEventsUseCase findAllUseCase;
    private final UpdateEventUseCase updateUseCase;
    private final DeleteEventUseCase deleteUseCase;
    private final EventWebMapper mapper;

    @PostMapping
    @Operation(summary = "Crear un nuevo evento")
    public ResponseEntity<EventResponse> create(@Valid @RequestBody EventRequest request) {
        Event domain = mapper.toDomain(request);
        Event created = createUseCase.createEvent(domain);
        return ResponseEntity.created(URI.create("/api/events/" + created.getId()))
                .body(mapper.toResponse(created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener evento por id")
    public ResponseEntity<EventResponse> getById(@PathVariable Long id) {
        Event event = findByIdUseCase.findEventById(id);
        return ResponseEntity.ok(mapper.toResponse(event));
    }

    @GetMapping
    @Operation(summary = "Listar todos los eventos")
    public ResponseEntity<List<EventResponse>> getAll() {
        List<Event> list = findAllUseCase.findAllEvents();
        return ResponseEntity.ok(list.stream().map(mapper::toResponse).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un evento")
    public ResponseEntity<EventResponse> update(@PathVariable Long id, @Valid @RequestBody EventRequest request) {
        Event domain = mapper.toDomain(request);
        Event updated = updateUseCase.updateEvent(id, domain);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un evento")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUseCase.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
