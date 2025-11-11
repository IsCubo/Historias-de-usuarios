package com.riwi.eventsvenues.service;

import java.util.List;

import com.riwi.eventsvenues.dto.VenueRequest;
import com.riwi.eventsvenues.dto.VenueResponse;

public interface IVenueService {

    VenueResponse create(VenueRequest venueRequest);

    List<VenueResponse> findAll();

    VenueResponse findById(Long id);

    VenueResponse update(Long id, VenueRequest venueRequest);

    void delete(Long id);

    VenueResponse getVenueWithEvents(Long id);
}

