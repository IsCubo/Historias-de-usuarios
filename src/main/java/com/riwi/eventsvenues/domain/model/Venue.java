package com.riwi.eventsvenues.domain.model;

import java.util.Objects;

public class Venue {
    private Long id;
    private String name;
    private String location;

    public Venue() {}
    public Venue(Long id, String name, String location) { this.id = id; this.name = name; this.location = location; }

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venue)) return false;
        Venue venue = (Venue) o;
        return id != null && id.equals(venue.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}
