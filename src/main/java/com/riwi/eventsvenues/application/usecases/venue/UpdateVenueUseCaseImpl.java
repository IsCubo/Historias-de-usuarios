package com.riwi.eventsvenues.application.usecases.venue;

import com.riwi.eventsvenues.domain.model.Venue;
import com.riwi.eventsvenues.domain.ports.in.venue.UpdateVenueUseCase;
import com.riwi.eventsvenues.domain.ports.out.VenueRepositoryPort;
import com.riwi.eventsvenues.infrastructure.exception.DuplicateNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateVenueUseCaseImpl implements UpdateVenueUseCase {

    private final VenueRepositoryPort repository;

    @Override
    public Venue updateVenue(Long id, Venue venue) {
        Venue existing = repository.findById(id);

        if (!existing.getName().equals(venue.getName())) {
            boolean nameExists = repository.findAll().stream()
                .anyMatch(v -> v.getName().equalsIgnoreCase(venue.getName()) && !v.getId().equals(id));

            if (nameExists) {
                throw new DuplicateNameException("A venue with the name '" + venue.getName() + "' already exists");
            }
        }

        existing.setName(venue.getName());
        existing.setLocation(venue.getLocation());
        return repository.save(existing);
    }
}
