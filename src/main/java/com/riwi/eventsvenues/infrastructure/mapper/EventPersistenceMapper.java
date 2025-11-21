package com.riwi.eventsvenues.infrastructure.mapper;
import com.riwi.eventsvenues.domain.model.Event;
import com.riwi.eventsvenues.infrastructure.entities.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {VenuePersistenceMapper.class})
public interface EventPersistenceMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    EventEntity toEntity(Event domain);

    @Mapping(target = "venue.id", source = "venue.id")
    Event toDomain(EventEntity entity);
}
