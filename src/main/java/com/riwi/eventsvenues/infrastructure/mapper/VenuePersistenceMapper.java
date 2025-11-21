package com.riwi.eventsvenues.infrastructure.mapper;

import com.riwi.eventsvenues.domain.model.Venue;
import com.riwi.eventsvenues.infrastructure.entities.VenueEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenuePersistenceMapper {
    VenueEntity toEntity(Venue domain);
    Venue toDomain(VenueEntity entity);
}