package com.riwi.eventsvenues.application.usecases.venue;

import com.riwi.eventsvenues.domain.model.Venue;
import com.riwi.eventsvenues.domain.ports.in.venue.UpdateVenueUseCase;
import com.riwi.eventsvenues.domain.ports.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateVenueUseCaseImpl implements UpdateVenueUseCase {

    private final VenueRepositoryPort repository;

    @Override
    @Transactional
    public Venue updateVenue(Long id, Venue venue) {
        Venue existing = repository.findById(id);
        existing.setName(venue.getName());
        existing.setLocation(venue.getLocation());
        return repository.save(existing);
    }
}
