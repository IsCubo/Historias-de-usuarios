package com.riwi.eventsvenues.service.impl;

import com.riwi.eventsvenues.dto.EventDTO;
import com.riwi.eventsvenues.exception.NotFoundException;
import com.riwi.eventsvenues.model.Event;
import com.riwi.eventsvenues.repository.IRepository;
import com.riwi.eventsvenues.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService implements IService<EventDTO, Long> {

    private final IRepository<Event, Long> repository;

    public EventService(IRepository<Event, Long> repository) {
        this.repository = repository;
    }

    public Event toEntity(EventDTO dto)
    {
        Event e = new Event();
        e.setId(dto.getId());
        e.setName(dto.getName());
        e.setDate(dto.getDate());
        e.setCapacity(dto.getCapacity());
        e.setCategory(dto.getCategory());
        return e;
    }

    public EventDTO toDTO(Event e)
    {
        EventDTO dto = new EventDTO();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setDate(e.getDate());
        dto.setCapacity(e.getCapacity());
        dto.setCategory(e.getCategory());
        return dto;
    }

    //@Transactional
    @Override
    public EventDTO create(EventDTO dto)
    {
        Event save = repository.save(toEntity(dto));
        return toDTO(save);
    }

    //@Transactional(readOnly = true)
    @Override
    public List<EventDTO> findAll()
    {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //@Transactional(readOnly = true)
    @Override
    public EventDTO findById(Long id)
    {
        return repository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Event with id: " + id));
    }

    //@Transactional
    @Override
    public EventDTO update(Long id, EventDTO dto)
    {
        return repository.findById(id).map(event -> {
            event.setName(dto.getName());
            event.setDate(dto.getDate());
            event.setCapacity(dto.getCapacity());
            event.setCategory(dto.getCategory());
            Event updated = repository.update(id, event)
                    .orElseThrow(() -> new NotFoundException("Event with id: " + id));
            return toDTO(updated);
        }).orElseThrow(() -> new NotFoundException("Event with id: " + id));
    }

    //@Transactional
    @Override
    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event with id: " + id));
        repository.delete(id);
    }
}
