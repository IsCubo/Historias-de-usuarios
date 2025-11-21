package com.riwi.eventsvenues.application.usecases.event;

import com.riwi.eventsvenues.domain.model.Event;
import com.riwi.eventsvenues.domain.model.Venue;
import com.riwi.eventsvenues.domain.ports.in.event.UpdateEventUseCase;
import com.riwi.eventsvenues.domain.ports.out.EventRepositoryPort;
import com.riwi.eventsvenues.domain.ports.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateEventUseCaseImpl implements UpdateEventUseCase {

    private final EventRepositoryPort eventRepo;
    private final VenueRepositoryPort venueRepo;

    @Override
    @Transactional
    public Event updateEvent(Long id, Event event) {
        Event existing = eventRepo.findById(id);
        if (event.getVenue() != null && !existing.getVenue().getId().equals(event.getVenue().getId())) {
            Venue newVenue = venueRepo.findById(event.getVenue().getId());
            existing.setVenue(newVenue);
        }
        existing.setName(event.getName());
        existing.setCategory(event.getCategory());
        existing.setCapacity(event.getCapacity());
        existing.setDate(event.getDate());
        existing.setUpdatedAt(LocalDateTime.now());
        return eventRepo.save(existing);
    }
}
