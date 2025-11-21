package com.riwi.eventsvenues.infrastructure.mapper;

import com.riwi.eventsvenues.domain.model.Venue;
import com.riwi.eventsvenues.infrastructure.dto.VenueRequest;
import com.riwi.eventsvenues.infrastructure.dto.VenueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VenueWebMapper {
    @Mapping(target = "id", ignore = true)
    Venue toDomain(VenueRequest request);
    VenueResponse toResponse(Venue domain);
}
