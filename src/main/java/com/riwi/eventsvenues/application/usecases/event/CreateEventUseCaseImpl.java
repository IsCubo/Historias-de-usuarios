package com.riwi.eventsvenues.application.usecases.event;

import com.riwi.eventsvenues.domain.model.Event;
import com.riwi.eventsvenues.domain.model.Venue;
import com.riwi.eventsvenues.domain.ports.in.event.CreateEventUseCase;
import com.riwi.eventsvenues.domain.ports.out.EventRepositoryPort;
import com.riwi.eventsvenues.domain.ports.out.VenueRepositoryPort;
import com.riwi.eventsvenues.infrastructure.exception.DuplicateNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateEventUseCaseImpl implements CreateEventUseCase {

    private final EventRepositoryPort eventRepository;
    private final VenueRepositoryPort venueRepository;

    @Override
    public Event createEvent(Event event) {
        boolean nameExists = eventRepository.findAll().stream()
            .anyMatch(e -> e.getName().equalsIgnoreCase(event.getName()));
        
        if (nameExists) {
            throw new DuplicateNameException("An event with the name '" + event.getName() + "' already exists");
        }

        Long venueId = event.getVenue() != null ? event.getVenue().getId() : null;
        if (venueId == null) {
            throw new IllegalArgumentException("venueId is required");
        }
        Venue venue = venueRepository.findById(venueId);
        event.setVenue(venue);
        event.setCreatedAt(LocalDateTime.now());
        event.setUpdatedAt(LocalDateTime.now());
        return eventRepository.save(event);
    }
}
