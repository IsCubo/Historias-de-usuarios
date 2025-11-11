package com.riwi.eventsvenues.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.riwi.eventsvenues.dto.VenueRequest;
import com.riwi.eventsvenues.dto.VenueResponse;
import com.riwi.eventsvenues.entity.VenueEntity;

@Mapper(componentModel = "spring")
public interface VenueMapper{

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    VenueEntity toEntity(VenueRequest request);
    
    VenueResponse toResponse(VenueEntity entity);
}
