package com.example.orders_manage_system.dao;

import com.example.orders_manage_system.common.DataNotFoundException;

import java.util.List;

public interface BaseDao<T> {
    List<T> findAll();

    T findById(Integer id) throws DataNotFoundException;

    void save(T t);

    void deleteById(Integer id);
}
