package com.riwi.eventsvenues.infrastructure.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for venue management")
public record VenueRequest(
        @Schema(description = "Name of the venue", example = "Main Theater", required = true)
        @NotBlank @Size(min = 3, max = 150)
        String name,

        @Schema(description = "Location of the venue", example = "123 Main St", required = true)
        @NotBlank
        String location
) {}
