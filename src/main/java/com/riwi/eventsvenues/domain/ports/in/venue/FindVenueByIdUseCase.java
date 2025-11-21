package com.riwi.eventsvenues.domain.ports.in.venue;

import com.riwi.eventsvenues.domain.model.Venue;

public interface FindVenueByIdUseCase {
    Venue findVenueById(Long id);
}
