package com.riwi.eventsvenues.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for venue response")
public record VenueResponse(
        Long id,
        String name,
        String location
) {}
