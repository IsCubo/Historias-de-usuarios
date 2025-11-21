package com.riwi.eventsvenues.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event {
    private Long id;
    private String name;
    private LocalDateTime date;
    private Integer capacity;
    private String category;
    private Venue venue;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Event() {}

    public Event(Long id, String name, LocalDateTime date, Integer capacity, String category, Venue venue,
                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id; this.name = name; this.date = date; this.capacity = capacity;
        this.category = category; this.venue = venue; this.createdAt = createdAt; this.updatedAt = updatedAt;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Venue getVenue() { return venue; }
    public void setVenue(Venue venue) { this.venue = venue; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return id != null && id.equals(event.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
