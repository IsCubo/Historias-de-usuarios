package com.riwi.eventsvenues.domain.ports.in.event;

import com.riwi.eventsvenues.domain.model.Event;

public interface CreateEventUseCase {
    Event createEvent(Event event);
}
