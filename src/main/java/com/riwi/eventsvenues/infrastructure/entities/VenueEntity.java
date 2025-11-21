package com.riwi.eventsvenues.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "venues")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VenueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String name;

    @Column(nullable = false)
    private String location;

    @JsonManagedReference
    @OneToMany(mappedBy = "venue", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventEntity> events;
}


