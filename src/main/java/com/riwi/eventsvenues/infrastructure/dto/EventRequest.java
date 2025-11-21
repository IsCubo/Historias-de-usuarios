package com.riwi.eventsvenues.infrastructure.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "DTO for event management")
public record EventRequest(
        @Schema(description = "Name of the event", example = "Rock Concert", required = true)
        @NotBlank @Size(min = 3, max = 150)
        String name,

        @Schema(description = "Date and time of the event (ISO)", example = "2025-12-31T20:00:00", required = true)
        @NotNull
        LocalDateTime date,

        @Schema(description = "Capacity of the event", example = "1000", required = true)
        @NotNull @Positive
        Integer capacity,

        @Schema(description = "Category of the event", example = "Music")
        String category,

        @Schema(description = "Venue ID of the event", example = "1", required = true)
        @NotNull
        Long venueId
) {}
