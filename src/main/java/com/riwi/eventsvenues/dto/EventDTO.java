package com.riwi.eventsvenues.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for event management")
public class EventDTO {

    @Schema(description = "Unique ID of the event", example = "1")
    private Long id;

    @Schema(description = "Name of the event", example = "Rock Concert", required = true)
    @NotBlank(message = "name must not be blank")
    @Size(min = 3, max = 50, message = "The name must be between 3 and 50 characters")
    private String name;

    @Schema(description = "Date of the event (format: YYYY-MM-DD)", example = "2025-12-31", required = true)
    @NotNull(message = "Date is mandatory")
    @FutureOrPresent(message = "Date not be past")
    private LocalDate date;

    @Schema(description = "Capacity of the event", example = "1000", required = true, minimum = "1")
    @NotNull(message = "capacity is mandatory")
    @Positive(message = "Capacity should be positive number")
    private int capacity;

    @Schema(description = "Category of the event", example = "Music")
    private String category;
}
