package com.riwi.eventsvenues.model;

import java.time.LocalDate;

public class Event {
    private Long id;
    private String name;
    private LocalDate date;
    private int capacity;
    private String category;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Event(Long id, String name, LocalDate date, int capacity, String category, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.capacity = capacity;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
