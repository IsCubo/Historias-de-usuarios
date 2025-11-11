package com.riwi.eventsvenues.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO for venue management")
public record VenueRequest(
    @Schema(description = "Name of the venue", example = "Main Theater", required = true)
    @NotBlank(message = "name must not be blank")
    @Size(min = 3, max = 50, message = "The name must be between 3 and 50 characters")
    String name,

    @Schema(description = "Location of the venue", example = "123 Main St", required = true)
    @NotBlank(message = "location must not be blank")
    String location
) {}
