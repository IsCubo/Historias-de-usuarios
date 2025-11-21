package com.riwi.eventsvenues.application.usecases.venue;

import com.riwi.eventsvenues.domain.model.Venue;
import com.riwi.eventsvenues.domain.ports.in.venue.FindAllVenuesUseCase;
import com.riwi.eventsvenues.domain.ports.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllVenuesUseCaseImpl implements FindAllVenuesUseCase {

    private final VenueRepositoryPort repository;

    @Override
    public List<Venue> findAllVenues() {
        return repository.findAll();
    }
}
