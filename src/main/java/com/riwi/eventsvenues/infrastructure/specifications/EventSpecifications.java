package com.riwi.eventsvenues.infrastructure.specifications;

import com.riwi.eventsvenues.infrastructure.entities.EventEntity;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDateTime;

public class EventSpecifications {
    
    public static Specification<EventEntity> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> 
            category == null ? null : criteriaBuilder.equal(root.get("category"), category);
    }
    
    public static Specification<EventEntity> dateAfter(LocalDateTime date) {
        return (root, query, criteriaBuilder) -> 
            date == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("date"), date);
    }
    
    public static Specification<EventEntity> dateBefore(LocalDateTime date) {
        return (root, query, criteriaBuilder) -> 
            date == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("date"), date);
    }
}