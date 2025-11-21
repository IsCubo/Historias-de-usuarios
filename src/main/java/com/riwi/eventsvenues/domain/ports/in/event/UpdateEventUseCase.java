package com.riwi.eventsvenues.domain.ports.in.event;

import com.riwi.eventsvenues.domain.model.Event;

public interface UpdateEventUseCase {
    Event updateEvent(Long id, Event event);
}
