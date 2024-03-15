package com.example.orders_manage_system.service;

import com.example.orders_manage_system.common.DataNotFoundException;
import com.example.orders_manage_system.dao.BaseDao;
import com.example.orders_manage_system.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements BaseService<Product> {
    @Autowired
    private BaseDao<Product> dao;

    @Override
    public List<Product> findAll() {
        return dao.findAll();
    }

    @Override
    public Product findById(Integer id) throws DataNotFoundException {
        return dao.findById(id);
    }

    @Override
    public void save(Product product) {
        dao.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }
}
