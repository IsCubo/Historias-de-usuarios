package com.riwi.eventsvenues.application.usecases.event;

import com.riwi.eventsvenues.domain.model.Event;
import com.riwi.eventsvenues.domain.ports.in.event.FindEventByIdUseCase;
import com.riwi.eventsvenues.domain.ports.out.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindEventByIdUseCaseImpl implements FindEventByIdUseCase {

    private final EventRepositoryPort repo;

    @Override
    public Event findEventById(Long id) {
        return repo.findByIdWithVenue(id);
    }
}
