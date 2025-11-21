package com.riwi.eventsvenues.infrastructure.repositories;

import com.riwi.eventsvenues.infrastructure.entities.VenueEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VenueJpaRepository extends JpaRepository<VenueEntity, Long> {

    @Query("SELECT v FROM VenueEntity v LEFT JOIN FETCH v.events WHERE v.id = :id")
    Optional<VenueEntity> findByIdWithEvents(@Param("id") Long id);
}
