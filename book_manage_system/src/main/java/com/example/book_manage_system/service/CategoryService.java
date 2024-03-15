package com.example.book_manage_system.service;

import com.example.book_manage_system.common.DataNotFoundException;
import com.example.book_manage_system.dao.BaseDao;
import com.example.book_manage_system.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements BaseService<Category> {
    @Autowired
    private BaseDao<Category> categoryDao;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(Integer id) throws DataNotFoundException {
        return categoryDao.findById(id);
    }

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void delete(Integer id) {
        categoryDao.delete(id);
    }
}
