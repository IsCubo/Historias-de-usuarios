package com.riwi.eventsvenues.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;


@Schema(description = "DTO for event management")
public record EventResponse(
    @Schema(description = "Unique ID of the event", example = "1")
    Long id,

    @Schema(description = "Name of the event", example = "Rock Concert", required = true)
    String name,

    @Schema(description = "Date of the event (format: YYYY-MM-DD)", example = "2025-12-31", required = true)
    LocalDate date,

    @Schema(description = "Capacity of the event", example = "1000", required = true, minimum = "1")
    Integer capacity,

    @Schema(description = "Category of the event", example = "Music")
    String category,

    @Schema(description = "Venue of the event", example = "{id: 1, /nname: CC Carnaval, /nlocation: Barramquilla}")
    VenueResponse venue
) {}
