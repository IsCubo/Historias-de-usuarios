package com.riwi.eventsvenues.infrastructure.adapters;

import com.riwi.eventsvenues.domain.model.Event;
import com.riwi.eventsvenues.domain.ports.out.EventRepositoryPort;
import com.riwi.eventsvenues.infrastructure.entities.EventEntity;
import com.riwi.eventsvenues.infrastructure.exception.NotFoundException;
import com.riwi.eventsvenues.infrastructure.mapper.EventPersistenceMapper;
import com.riwi.eventsvenues.infrastructure.repositories.EventJpaRepository;
import com.riwi.eventsvenues.infrastructure.specifications.EventSpecifications;

import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventRepositoryAdapter implements EventRepositoryPort {

    private final EventJpaRepository repo;
    private final EventPersistenceMapper mapper;

    @Override
    public Event save(Event event) {
        EventEntity entity = mapper.toEntity(event);
        EventEntity saved = repo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Event findById(Long id) {
        return repo.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new NotFoundException("Event not found with id: " + id));
    }

    @Override
    public Event findByIdWithVenue(Long id) {
        return repo.findByIdWithVenue(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new NotFoundException("Event not found with id: " + id));
    }

    @Override
    public List<Event> findAll() {
        return repo.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @SuppressWarnings("removal")
    @Override
    public List<Event> findByFilters(String category, LocalDateTime startDate, LocalDateTime endDate) {
        Specification<EventEntity> spec = Specification.where(EventSpecifications.hasCategory(category))
                .and(EventSpecifications.dateAfter(startDate))
                .and(EventSpecifications.dateBefore(endDate));
                
        return repo.findAll(spec).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Event not found with id: " + id);
        repo.deleteById(id);
    }
}
