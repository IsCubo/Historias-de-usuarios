package com.riwi.eventsvenues.domain.ports.in.event;

import java.util.List;
import com.riwi.eventsvenues.domain.model.Event;

public interface FindAllEventsUseCase {
    List<Event> findAllEvents();
}
