package com.riwi.eventsvenues.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.riwi.eventsvenues.entity.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    @Query("SELECT e FROM EventEntity e JOIN FETCH e.venue WHERE e.id = :eventId")
    Optional<EventEntity> findByIdWithVenue(@Param("eventId") Long eventId);

    @Query("SELECT DISTINCT e FROM EventEntity e JOIN FETCH e.venue")
    List<EventEntity> findAllWithVenue();

    EventEntity findByName(String name);
    
}
