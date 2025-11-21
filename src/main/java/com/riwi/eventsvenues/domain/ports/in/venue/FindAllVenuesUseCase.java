package com.riwi.eventsvenues.domain.ports.in.venue;

import java.util.List;
import com.riwi.eventsvenues.domain.model.Venue;

public interface FindAllVenuesUseCase {
    List<Venue> findAllVenues();
}
