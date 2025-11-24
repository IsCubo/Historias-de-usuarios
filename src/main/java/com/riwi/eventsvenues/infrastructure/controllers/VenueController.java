package com.riwi.eventsvenues.infrastructure.controllers;

import com.riwi.eventsvenues.domain.model.Venue;
import com.riwi.eventsvenues.domain.ports.in.venue.*;
import com.riwi.eventsvenues.infrastructure.dto.VenueRequest;
import com.riwi.eventsvenues.infrastructure.dto.VenueResponse;
import com.riwi.eventsvenues.infrastructure.mapper.VenueWebMapper;
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
@RequestMapping("/api/v1/venues")
@Tag(name = "Venues", description = "Endpoints para la gestión de venues")
@RequiredArgsConstructor
public class VenueController {

    private final CreateVenueUseCase createUseCase;
    private final FindVenueByIdUseCase findByIdUseCase;
    private final FindAllVenuesUseCase findAllUseCase;
    private final UpdateVenueUseCase updateUseCase;
    private final DeleteVenueUseCase deleteUseCase;
    private final VenueWebMapper mapper;

    @PostMapping
    @Operation(summary = "Crear un nuevo venue")
    public ResponseEntity<VenueResponse> create(@Valid @RequestBody VenueRequest request) {
        Venue domain = mapper.toDomain(request);
        Venue created = createUseCase.createVenue(domain);
        return ResponseEntity.created(URI.create("/api/venues/" + created.getId()))
                .body(mapper.toResponse(created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener venue por id")
    public ResponseEntity<VenueResponse> getById(@PathVariable Long id) {
        Venue venue = findByIdUseCase.findVenueById(id);
        return ResponseEntity.ok(mapper.toResponse(venue));
    }

    @GetMapping
    @Operation(summary = "Listar todos los venues")
    public ResponseEntity<List<VenueResponse>> getAll() {
        List<Venue> list = findAllUseCase.findAllVenues();
        return ResponseEntity.ok(list.stream().map(mapper::toResponse).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un venue")
    public ResponseEntity<VenueResponse> update(@PathVariable Long id, @Valid @RequestBody VenueRequest request) {
        Venue domain = mapper.toDomain(request);
        Venue updated = updateUseCase.updateVenue(id, domain);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un venue")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUseCase.deleteVenue(id);
        return ResponseEntity.noContent().build();
    }
}
