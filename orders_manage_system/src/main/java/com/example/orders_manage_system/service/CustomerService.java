package com.example.orders_manage_system.service;

import com.example.orders_manage_system.common.DataNotFoundException;
import com.example.orders_manage_system.dao.BaseDao;
import com.example.orders_manage_system.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements BaseService<Customer> {
    @Autowired
    private BaseDao<Customer> dao;

    @Override
    public List<Customer> findAll() {
        return dao.findAll();
    }

    @Override
    public Customer findById(Integer id) throws DataNotFoundException {
        return dao.findById(id);
    }

    @Override
    public void save(Customer customer) {
        dao.save(customer);
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }
}
