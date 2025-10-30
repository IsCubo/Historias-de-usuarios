package com.riwi.eventsvenues.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueDTO {

    private Long id;
    @NotBlank(message = "name must not be blank")
    private String name;
    private String location;

}
