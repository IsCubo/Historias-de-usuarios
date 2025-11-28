package com.riwi.eventsvenues.infrastructure.repositories;

import com.riwi.eventsvenues.infrastructure.entities.EventEntity;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventJpaRepository extends JpaRepository<EventEntity, Long> {

    @EntityGraph(attributePaths = {"venue"})
    @Query("SELECT e FROM EventEntity e WHERE e.id = :id")
    Optional<EventEntity> findByIdWithVenue(@Param("id") Long id);

    @EntityGraph(attributePaths = {"venue"})
    List<EventEntity> findAll(Specification<EventEntity> spec);
}
