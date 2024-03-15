package com.example.book_manage_system.service;

import com.example.book_manage_system.common.DataNotFoundException;

import java.util.List;

public interface BaseService<T> {
    List<T> findAll();

    T findById(Integer id) throws DataNotFoundException;

    void save(T t);

    void delete(Integer id);
}
