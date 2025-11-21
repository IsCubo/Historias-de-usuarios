package com.riwi.eventsvenues.infrastructure.mapper;

import com.riwi.eventsvenues.domain.model.Event;
import com.riwi.eventsvenues.infrastructure.dto.EventRequest;
import com.riwi.eventsvenues.infrastructure.dto.EventResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {VenueWebMapper.class})
public interface EventWebMapper {

    @Mapping(target = "venue.id", source = "venueId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Event toDomain(EventRequest request);

    EventResponse toResponse(Event domain);
}
