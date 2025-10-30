package com.riwi.eventsvenues.service;

import java.util.List;

public interface IService<T, Long> {
    T create(T t);
    List<T> findAll();
    T findById(Long id);
    T update(Long id, T t);
    void delete(Long id);
}
