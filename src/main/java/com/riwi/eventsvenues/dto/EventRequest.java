package com.riwi.eventsvenues.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for event management")
public record EventRequest(

    @Schema(description = "Name of the event", example = "Rock Concert", required = true)
    @NotBlank(message = "name must not be blank")
    @Size(min = 3, max = 50, message = "The name must be between 3 and 50 characters")
    String name,

    @Schema(description = "Date of the event (format: YYYY-MM-DD)", example = "2025-12-31", required = true)
    @NotNull(message = "Date is mandatory")
    @FutureOrPresent(message = "Date not be past")
    LocalDate date,

    @Schema(description = "Capacity of the event", example = "1000", required = true, minimum = "1")
    @NotNull(message = "capacity is mandatory")
    @Positive(message = "Capacity should be positive number")
    Integer capacity,

    @NotNull(message = "category is mandatory")
    @Schema(description = "Category of the event", example = "Music")
    String category,

    @Schema(description = "Venue ID of the event", example = "1", required = true)
    @NotNull(message = "venue id is mandatory")
    Long venueId

    ) {}
