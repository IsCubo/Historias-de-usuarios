package com.riwi.eventsvenues.service;

import java.util.List;

import com.riwi.eventsvenues.dto.EventRequest;
import com.riwi.eventsvenues.dto.EventResponse;

public interface IEventService {

    EventResponse create(EventRequest request);

    EventResponse findById(Long id);

    List<EventResponse> findAll();

    EventResponse update(Long id, EventRequest request);

    void delete(Long id);

}
