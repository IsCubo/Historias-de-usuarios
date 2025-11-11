package com.riwi.eventsvenues.service.impl;

import com.riwi.eventsvenues.dto.EventRequest;
import com.riwi.eventsvenues.dto.EventResponse;
import com.riwi.eventsvenues.entity.EventEntity;
import com.riwi.eventsvenues.entity.VenueEntity;
import com.riwi.eventsvenues.exception.DuplicateNameException;
import com.riwi.eventsvenues.exception.NotFoundException;
import com.riwi.eventsvenues.mapper.EventMapper;
import com.riwi.eventsvenues.repository.EventRepository;
import com.riwi.eventsvenues.repository.VenueRepository;
import com.riwi.eventsvenues.service.IEventService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventService implements IEventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final EventMapper mapper;


    @Transactional
    @Override
    public EventResponse create(EventRequest request) 
    {
        VenueEntity venue = venueRepository.findById(request.venueId())
                .orElseThrow(() -> new NotFoundException("Venue not found with id: " + request.venueId()));

        if(eventRepository.findByName(request.name()) != null)
        {
            throw new DuplicateNameException("Event name already exists");
        }

        EventEntity event = mapper.toEntity(request);
        event.setVenue(venue);

        EventEntity saved = eventRepository.save(event);
        return mapper.toResponse(saved);
    }

    @Override
    public List<EventResponse> findAll()
    {
        return eventRepository.findAllWithVenue()
            .stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public EventResponse findById(Long id)
    {
        EventEntity event = eventRepository.findByIdWithVenue(id)
                .orElseThrow(() -> new NotFoundException("Event not found with id: " + id));
        return mapper.toResponse(event);
    }

    @Override
    @Transactional
    public EventResponse update(Long id, EventRequest request) 
    {
        EventEntity entity = eventRepository.findByIdWithVenue(id)
            .orElseThrow(() -> new NotFoundException("Event not found with id: " + id));
        
        if(eventRepository.findByName(request.name()) != null)
        {
            throw new DuplicateNameException("Event name already exists");
        }

        if (!entity.getVenue().getId().equals(request.venueId())) {
            VenueEntity newVenue = venueRepository.findById(request.venueId())
                    .orElseThrow(() -> new NotFoundException("Venue not found with id: " + request.venueId()));
            entity.setVenue(newVenue);
        }

        entity.setName(request.name());
        entity.setCapacity(request.capacity());
        entity.setDate(request.date());
        entity.setCategory(request.category());
        return mapper.toResponse(eventRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) 
    {
        eventRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Event not found with id: " + id));
        eventRepository.deleteById(id);
    }

}
