package com.riwi.eventsvenues.dto;

import com.riwi.eventsvenues.model.Venue;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private Long id;
    @NotBlank(message = "name must not be blank")
    private String name;
    private String date;
    private int capacity;
    private String category;

}
