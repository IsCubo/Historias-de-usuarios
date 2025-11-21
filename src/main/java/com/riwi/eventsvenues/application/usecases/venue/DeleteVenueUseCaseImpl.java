package com.riwi.eventsvenues.application.usecases.venue;

import com.riwi.eventsvenues.domain.ports.in.venue.DeleteVenueUseCase;
import com.riwi.eventsvenues.domain.ports.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteVenueUseCaseImpl implements DeleteVenueUseCase {

    private final VenueRepositoryPort repository;

    @Override
    @Transactional
    public void deleteVenue(Long id) {
        repository.findById(id);
        repository.deleteById(id);
    }
}
