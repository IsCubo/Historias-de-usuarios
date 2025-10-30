package com.riwi.eventsvenues.repository.impl;

import com.riwi.eventsvenues.model.Event;
import com.riwi.eventsvenues.repository.IRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EventRepository implements IRepository<Event, Long> {

    private final List<Event> events = new CopyOnWriteArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Event save(Event event)
    {
        event.setId(idCounter.getAndIncrement());
        event.setCreatedAt(LocalDate.now());
        events.add(event);
        return event;
    }

    @Override
    public Optional<Event> findById(Long id)
    {
        return events.stream()
                .filter(e -> id.equals(e.getId()))
                .findFirst();
    }

    @Override
    public List<Event> findAll()
    {
        return new ArrayList<>(events);
    }

    @Override
    public void delete(Long id)
    {
        events.removeIf(event -> event.getId().equals(id));
    }

    @Override
    public Optional<Event> update(Long id, Event event)
    {
        return findById(id)
                .map(existing -> {
            existing.setName(event.getName());
            existing.setDate(event.getDate());
            existing.setCapacity(event.getCapacity());
            existing.setCategory(event.getCategory());
            existing.setUpdatedAt(LocalDate.now());
            return existing;
        });
    }
}
