package com.riwi.eventsvenues.controller;

import com.riwi.eventsvenues.dto.VenueDTO;
import com.riwi.eventsvenues.service.impl.VenueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {

    private final VenueService venueService;

    @Autowired
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public ResponseEntity<List<VenueDTO>> findAll()
    {
        List<VenueDTO> events = venueService.findAll();
        return ResponseEntity.ok().body(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueDTO> findById(@Valid @PathVariable Long id)
    {
        VenueDTO event = venueService.findById(id);
        return ResponseEntity.ok().body(event);
    }

    @PostMapping
    public ResponseEntity<VenueDTO> save(@Valid @RequestBody VenueDTO eventDTO)
    {
        VenueDTO savedEvent = venueService.create(eventDTO);
        return ResponseEntity.ok().body(savedEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenueDTO> update(@Valid @PathVariable Long id, @Valid @RequestBody VenueDTO eventDTO)
    {
        VenueDTO updatedEvent = venueService.update(id, eventDTO);
        return ResponseEntity.ok().body(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        venueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
