package com.example.orders_manage_system.dao;

import com.example.orders_manage_system.common.DataNotFoundException;
import com.example.orders_manage_system.entity.Order;
import com.example.orders_manage_system.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDao implements BaseDao<Order> {
    @Autowired
    OrderRepository repository;

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order findById(Integer id) throws DataNotFoundException {
        return repository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public void save(Order order) {
        this.repository.save(order);
    }

    @Override
    public void deleteById(Integer id) {
        try {
            Order order = this.findById(id);
            this.repository.deleteById(order.getId());
        } catch (DataNotFoundException e) {
            System.out.println("no data");
        }
    }
}
