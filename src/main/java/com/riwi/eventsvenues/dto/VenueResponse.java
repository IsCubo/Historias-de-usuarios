package com.riwi.eventsvenues.dto;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "DTO for venue management")
public record VenueResponse(

    @Schema(description = "Unique ID of the venue", example = "1")
    Long id,

    @Schema(description = "Name of the venue", example = "Main Theater", required = true)
    String name,

    @Schema(description = "Location of the venue", example = "123 Main St", required = true)
    String location
    
){}
