package com.example.enkai.service;

import com.example.enkai.common.DataNotFoundException;

import java.util.List;

public interface BaseService<T> {
    List<T> findAll();

    T findById(Integer id) throws DataNotFoundException;

    void save(T t);

    void deleteById(Integer id);
}
