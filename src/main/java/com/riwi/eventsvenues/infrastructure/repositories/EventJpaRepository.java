package com.riwi.eventsvenues.infrastructure.repositories;

import com.riwi.eventsvenues.infrastructure.entities.EventEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EventJpaRepository extends JpaRepository<EventEntity, Long> {

    @Query("SELECT e FROM EventEntity e JOIN FETCH e.venue WHERE e.id = :id")
    Optional<EventEntity> findByIdWithVenue(@Param("id") Long id);
}
