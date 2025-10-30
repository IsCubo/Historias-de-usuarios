package com.riwi.eventsvenues.service.impl;

import com.riwi.eventsvenues.dto.VenueDTO;
import com.riwi.eventsvenues.exception.NotFoundException;
import com.riwi.eventsvenues.model.Venue;
import com.riwi.eventsvenues.repository.IRepository;
import com.riwi.eventsvenues.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VenueService implements IService<VenueDTO, Long> {

    private final IRepository<Venue, Long> repository;

    public VenueService(IRepository<Venue, Long> repository) {
        this.repository = repository;
    }

    public Venue toEntity(VenueDTO dto)
    {
        Venue e = new Venue();
        e.setId(dto.getId());
        e.setName(dto.getName());
        e.setLocation(dto.getLocation());
        return e;
    }

    public VenueDTO toDTO(Venue e)
    {
        VenueDTO dto = new VenueDTO();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setLocation(e.getLocation());
        return dto;
    }

    //@Transactional
    @Override
    public VenueDTO create(VenueDTO dto)
    {
        Venue save = repository.save(toEntity(dto));
        return toDTO(save);
    }

    //@Transactional(readOnly = true)
    @Override
    public List<VenueDTO> findAll()
    {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //@Transactional(readOnly = true)
    @Override
    public VenueDTO findById(Long id)
    {
        return repository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new  NotFoundException("Event with id: " + id));
    }

    //@Transactional
    @Override
    public VenueDTO update(Long id, VenueDTO dto)
    {
        return repository.findById(id).map(venue -> {
            venue.setName(dto.getName());
            venue.setLocation(dto.getLocation());
            Venue updatedVenue = repository.update(id, venue)
                    .orElseThrow(() -> new NotFoundException("venue with id: " + id));
            return toDTO(updatedVenue);
        }).orElseThrow(() -> new NotFoundException("venue with id: " + id));
    }

    //@Transactional
    @Override
    public void delete(Long id)
    {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venue with id: " + id));
        repository.delete(id);
    }

}
