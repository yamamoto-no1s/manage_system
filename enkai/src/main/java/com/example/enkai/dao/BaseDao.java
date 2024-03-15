package com.example.enkai.dao;

import com.example.enkai.common.DataNotFoundException;

import java.util.List;

public interface BaseDao<T> {
    List<T> findAll();

    T findById(Integer id) throws DataNotFoundException;

    void save(T t);

    void deleteById(Integer id);
}
