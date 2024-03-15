package com.example.enkai.service;

import com.example.enkai.common.DataNotFoundException;
import com.example.enkai.dao.BaseDao;
import com.example.enkai.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements BaseService<Event> {
    @Autowired
    private BaseDao<Event> dao;

    @Override
    public List<Event> findAll() {
        return dao.findAll();
    }

    @Override
    public Event findById(Integer id) throws DataNotFoundException {
        return dao.findById(id);
    }

    @Override
    public void save(Event event) {
        dao.save(event);
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }
}
