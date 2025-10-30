package com.riwi.eventsvenues.repository.impl;

import com.riwi.eventsvenues.model.Venue;
import com.riwi.eventsvenues.repository.IRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class VenueRepository implements IRepository<Venue, Long> {

    private final List<Venue> venues = new CopyOnWriteArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Venue save(Venue venue)
    {
        venue.setId(idCounter.getAndIncrement());
        venue.setCreatedAt(LocalDate.now());
        venues.add(venue);
        return venue;
    }

    @Override
    public Optional<Venue> findById(Long id)
    {
        return venues.stream()
                .filter(venue -> venue.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Venue> findAll()
    {
        return new ArrayList<>(venues);
    }

    @Override
    public void delete(Long id)
    {
        venues.removeIf(venue -> venue.getId().equals(id));
    }

    @Override
    public Optional<Venue> update(Long id, Venue venue)
    {
        return findById(id)
                .map(existing -> {
                    existing.setName(venue.getName());
                    existing.setLocation(venue.getLocation());
                    existing.setUpdatedAt(LocalDate.now());
                    return existing;
                });
    }
}
