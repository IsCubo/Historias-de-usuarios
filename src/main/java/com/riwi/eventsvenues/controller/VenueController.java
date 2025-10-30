package com.riwi.eventsvenues.controller;

import com.riwi.eventsvenues.dto.VenueDTO;
import com.riwi.eventsvenues.service.impl.VenueService;
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
@RequestMapping("/api/v1/venues")
@Tag(name = "Venues", description = "API for venue management")
public class VenueController {

    private final VenueService venueService;

    @Autowired
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @Operation(summary = "List all venues",
            description = "Get a list of all available venues")
    @ApiResponse(responseCode = "200", description = "List of venues successfully retrieved")
    @GetMapping
    public ResponseEntity<List<VenueDTO>> findAll() {
        List<VenueDTO> venues = venueService.findAll();
        return ResponseEntity.ok().body(venues);
    }

    @Operation(summary = "Find venue by ID",
            description = "Get a specific venue by its ID")
    @ApiResponse(responseCode = "200", description = "Venue found")
    @ApiResponse(responseCode = "404", description = "Venue not found")
    @GetMapping("/{id}")
    public ResponseEntity<VenueDTO> findById(
            @Parameter(description = "ID of the venue to search for")
            @PathVariable Long id) {
        VenueDTO venue = venueService.findById(id);
        return ResponseEntity.ok().body(venue);
    }

    @Operation(summary = "Create new venue",
            description = "Create a new venue with the provided data")
    @ApiResponse(responseCode = "201", description = "Venue created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid venue data")
    @PostMapping
    public ResponseEntity<VenueDTO> save(
            @Parameter(description = "Data of the venue to create")
            @Valid @RequestBody VenueDTO venueDTO) {
        VenueDTO savedVenue = venueService.create(venueDTO);
        return ResponseEntity.status(201).body(savedVenue);
    }

    @Operation(summary = "Update venue",
            description = "Update an existing venue by its ID")
    @ApiResponse(responseCode = "200", description = "Venue updated successfully")
    @ApiResponse(responseCode = "404", description = "Venue not found")
    @PutMapping("/{id}")
    public ResponseEntity<VenueDTO> update(
            @Parameter(description = "ID of the venue to update")
            @PathVariable Long id,
            @Parameter(description = "New data for the venue")
            @Valid @RequestBody VenueDTO venueDTO) {
        VenueDTO updatedVenue = venueService.update(id, venueDTO);
        return ResponseEntity.ok().body(updatedVenue);
    }

    @Operation(summary = "Delete venue",
            description = "Delete an existing venue by its ID")
    @ApiResponse(responseCode = "204", description = "Venue deleted successfully")
    @ApiResponse(responseCode = "404", description = "Venue not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the venue to delete")
            @PathVariable Long id) {
        venueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
