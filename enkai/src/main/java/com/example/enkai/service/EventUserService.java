package com.example.enkai.service;

import com.example.enkai.common.DataNotFoundException;
import com.example.enkai.dao.BaseDao;
import com.example.enkai.entity.EventUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventUserService implements BaseService<EventUser> {
    @Autowired
    private BaseDao<EventUser> dao;

    @Override
    public List<EventUser> findAll() {
        return dao.findAll();
    }

    @Override
    public EventUser findById(Integer id) throws DataNotFoundException {
        return dao.findById(id);
    }

    @Override
    public void save(EventUser eventUser) {
        dao.save(eventUser);
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }
}
