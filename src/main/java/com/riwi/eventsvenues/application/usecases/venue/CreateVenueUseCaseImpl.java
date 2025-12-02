package com.riwi.eventsvenues.application.usecases.venue;

import com.riwi.eventsvenues.domain.model.Venue;
import com.riwi.eventsvenues.domain.ports.in.venue.CreateVenueUseCase;
import com.riwi.eventsvenues.domain.ports.out.VenueRepositoryPort;
import com.riwi.eventsvenues.infrastructure.exception.DuplicateNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateVenueUseCaseImpl implements CreateVenueUseCase {

    private final VenueRepositoryPort repository;

    @Override
    public Venue createVenue(Venue venue) {
        boolean nameExists = repository.findAll().stream()
            .anyMatch(v -> v.getName().equalsIgnoreCase(venue.getName()));
        
        if (nameExists) {
            throw new DuplicateNameException("A venue with the name '" + venue.getName() + "' already exists");
        }
        
        return repository.save(venue);
    }
}
