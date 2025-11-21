package com.riwi.eventsvenues.application.usecases.event;

import com.riwi.eventsvenues.domain.model.Event;
import com.riwi.eventsvenues.domain.ports.in.event.FindAllEventsUseCase;
import com.riwi.eventsvenues.domain.ports.out.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllEventsUseCaseImpl implements FindAllEventsUseCase {

    private final EventRepositoryPort repo;

    @Override
    public List<Event> findAllEvents() {
        return repo.findAll();
    }
}
