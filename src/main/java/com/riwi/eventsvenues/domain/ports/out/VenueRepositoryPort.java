package com.riwi.eventsvenues.domain.ports.out;

import com.riwi.eventsvenues.domain.model.Venue;
import java.util.List;

public interface VenueRepositoryPort {
    Venue save(Venue venue);
    Venue findById(Long id);
    List<Venue> findAll();
    void deleteById(Long id);
}
