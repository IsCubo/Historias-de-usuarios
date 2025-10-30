package com.riwi.eventsvenues.controller;

import com.riwi.eventsvenues.dto.EventDTO;
import com.riwi.eventsvenues.service.impl.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService)
    {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> findAll()
    {
        List<EventDTO> events = eventService.findAll();
        return ResponseEntity.ok().body(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> findById(@Valid @PathVariable Long id)
    {
        EventDTO event = eventService.findById(id);
        return ResponseEntity.ok().body(event);
    }

    @PostMapping
    public ResponseEntity<EventDTO> save(@Valid @RequestBody EventDTO eventDTO)
    {
        EventDTO savedEvent = eventService.create(eventDTO);
        return ResponseEntity.ok().body(savedEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> update(@Valid @PathVariable Long id, @Valid @RequestBody EventDTO eventDTO)
    {
        EventDTO updatedEvent = eventService.update(id, eventDTO);
        return ResponseEntity.ok().body(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
