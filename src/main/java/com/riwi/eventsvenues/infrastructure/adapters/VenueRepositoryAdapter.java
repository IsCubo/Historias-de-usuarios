package com.riwi.eventsvenues.infrastructure.adapters;

import com.riwi.eventsvenues.domain.model.Venue;
import com.riwi.eventsvenues.domain.ports.out.VenueRepositoryPort;
import com.riwi.eventsvenues.infrastructure.entities.VenueEntity;
import com.riwi.eventsvenues.infrastructure.exception.NotFoundException;
import com.riwi.eventsvenues.infrastructure.mapper.VenuePersistenceMapper;
import com.riwi.eventsvenues.infrastructure.repositories.VenueJpaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VenueRepositoryAdapter implements VenueRepositoryPort {

    private final VenueJpaRepository repo;
    private final VenuePersistenceMapper mapper;

    @Override
    public Venue save(Venue venue) {
        VenueEntity entity = mapper.toEntity(venue);
        VenueEntity saved = repo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Venue findById(Long id) {
        return repo.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new NotFoundException("Venue not found with id: " + id));
    }

    @Override
    public List<Venue> findAll() {
        return repo.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Venue not found with id: " + id);
        repo.deleteById(id);
    }
}
