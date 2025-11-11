package com.riwi.eventsvenues.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.riwi.eventsvenues.dto.EventRequest;
import com.riwi.eventsvenues.dto.EventResponse;
import com.riwi.eventsvenues.entity.EventEntity;

@Mapper(componentModel = "spring", uses = VenueMapper.class)
public interface EventMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "venue.id", source = "venueId")
    EventEntity toEntity(EventRequest eventRequest);

    EventResponse toResponse(EventEntity entity);

    List<EventResponse> toResponseList(List<EventEntity> entities);

}
