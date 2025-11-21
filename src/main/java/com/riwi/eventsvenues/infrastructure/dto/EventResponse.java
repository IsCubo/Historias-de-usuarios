package com.riwi.eventsvenues.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "DTO for event response")
public record EventResponse(
        @Schema(description = "Unique ID of the event", example = "1")
        Long id,

        @Schema(description = "Name of the event")
        String name,

        @Schema(description = "Date and time of the event")
        LocalDateTime date,

        Integer capacity,
        String category,
        VenueResponse venue
) {}
