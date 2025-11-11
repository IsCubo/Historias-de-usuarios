package com.riwi.eventsvenues.service.impl;

import com.riwi.eventsvenues.dto.VenueRequest;
import com.riwi.eventsvenues.dto.VenueResponse;
import com.riwi.eventsvenues.entity.VenueEntity;
import com.riwi.eventsvenues.exception.DuplicateNameException;
import com.riwi.eventsvenues.exception.NotFoundException;
import com.riwi.eventsvenues.mapper.VenueMapper;
import com.riwi.eventsvenues.repository.VenueRepository;
import com.riwi.eventsvenues.service.IVenueService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VenueService implements IVenueService 
{

    private final VenueRepository repository;
    private final VenueMapper mapper;

    @Override
    @Transactional
    public VenueResponse create(VenueRequest request) 
    {
        if(repository.findByName(request.name()) != null)
        {
            throw new DuplicateNameException("Venue name already exists");
        }

        VenueEntity entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));      
    }

    @Override
    public List<VenueResponse> findAll() 
    {
        return repository.findAll().stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public VenueResponse findById(Long id) 
    {
        return repository.findById(id).stream()
            .map(mapper::toResponse)
            .findFirst()
            .orElseThrow(() -> new NotFoundException("Venue not found"));
    }

    @Override
    @Transactional
    public VenueResponse update(Long id, VenueRequest request) 
    {
        VenueEntity entity = repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Venue not found"));

        if(repository.findByName(request.name()) != null)
        {
            throw new DuplicateNameException("Venue name already exists");
        }
        entity.setName(request.name());
        entity.setLocation(request.location());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) 
    {
        repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Venue not found"));
        repository.deleteById(id);
    }

    @Override
    public VenueResponse getVenueWithEvents(Long id) 
    {
        return repository.findByIdWithEvents(id).stream()
            .map(mapper::toResponse)
            .findFirst()
            .orElseThrow(() -> new NotFoundException("Venue not found"));
    }

}
