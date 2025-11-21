package com.riwi.eventsvenues.application.usecases.venue;

import com.riwi.eventsvenues.domain.model.Venue;
import com.riwi.eventsvenues.domain.ports.in.venue.FindVenueByIdUseCase;
import com.riwi.eventsvenues.domain.ports.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindVenueByIdUseCaseImpl implements FindVenueByIdUseCase {

    private final VenueRepositoryPort repository;

    @Override
    public Venue findVenueById(Long id) {
        return repository.findById(id);
    }
}
