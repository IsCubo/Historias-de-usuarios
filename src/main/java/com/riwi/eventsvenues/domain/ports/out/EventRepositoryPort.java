package com.riwi.eventsvenues.domain.ports.out;

import com.riwi.eventsvenues.domain.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepositoryPort {
    Event save(Event event);
    Event findById(Long id); // throws ResourceNotFoundException in adapter if not found
    Event findByIdWithVenue(Long id);
    List<Event> findAll();
    List<Event> findByFilters(String category, LocalDateTime startDate, LocalDateTime endDate);
    void deleteById(Long id);
}
