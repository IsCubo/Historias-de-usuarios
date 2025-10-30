package com.riwi.eventsvenues.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T, Long> {
    T save(T t);
    Optional<T> findById(Long id);
    List<T> findAll();
    void delete(Long id);
    Optional<T> update(Long id, T t);
}
