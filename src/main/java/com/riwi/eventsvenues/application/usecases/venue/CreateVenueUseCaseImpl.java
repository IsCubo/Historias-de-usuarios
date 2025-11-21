package com.riwi.eventsvenues.application.usecases.venue;

import com.riwi.eventsvenues.domain.model.Venue;
import com.riwi.eventsvenues.domain.ports.in.venue.CreateVenueUseCase;
import com.riwi.eventsvenues.domain.ports.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateVenueUseCaseImpl implements CreateVenueUseCase {

    private final VenueRepositoryPort repository;

    @Override
    @Transactional
    public Venue createVenue(Venue venue) {
        return repository.save(venue);
    }
}
