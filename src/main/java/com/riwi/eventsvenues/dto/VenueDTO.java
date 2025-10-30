package com.riwi.eventsvenues.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for venue management")
public class VenueDTO {

    @Schema(description = "Unique ID of the venue", example = "1")
    private Long id;

    @Schema(description = "Name of the venue", example = "Main Theater", required = true)
    @NotBlank(message = "name must not be blank")
    @Size(min = 3, max = 50, message = "The name must be between 3 and 50 characters")
    private String name;

    @Schema(description = "Location of the venue", example = "123 Main St", required = true)
    @NotBlank(message = "location must not be blank")
    private String location;
}
