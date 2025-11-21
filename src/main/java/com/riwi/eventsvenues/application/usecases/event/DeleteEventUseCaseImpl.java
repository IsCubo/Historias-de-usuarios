package com.riwi.eventsvenues.application.usecases.event;

import com.riwi.eventsvenues.domain.ports.in.event.DeleteEventUseCase;
import com.riwi.eventsvenues.domain.ports.out.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteEventUseCaseImpl implements DeleteEventUseCase {

    private final EventRepositoryPort repo;

    @Override
    @Transactional
    public void deleteEvent(Long id) {
        repo.findById(id);
        repo.deleteById(id);
    }
}
