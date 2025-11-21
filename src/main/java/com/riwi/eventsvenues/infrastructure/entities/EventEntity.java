package com.riwi.eventsvenues.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false, length = 100)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", nullable = false)
    @JsonBackReference
    private VenueEntity venue;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}


