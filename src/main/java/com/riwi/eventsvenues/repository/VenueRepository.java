package com.riwi.eventsvenues.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.riwi.eventsvenues.entity.VenueEntity;

public interface VenueRepository extends JpaRepository<VenueEntity, Long> {

    @Query("SELECT v FROM VenueEntity v LEFT JOIN FETCH v.events WHERE v.id = :venueId")
    Optional<VenueEntity> findByIdWithEvents(@Param("venueId") Long venueId);

    @Query("SELECT DISTINCT v FROM VenueEntity v LEFT JOIN FETCH v.events")
    List<VenueEntity> findAllWithEvents();

    VenueEntity findByName(String name);
}
