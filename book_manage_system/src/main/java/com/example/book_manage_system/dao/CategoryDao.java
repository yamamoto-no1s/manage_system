package com.example.book_manage_system.dao;

import com.example.book_manage_system.common.DataNotFoundException;
import com.example.book_manage_system.entity.Category;
import com.example.book_manage_system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao implements BaseDao<Category> {
    @Autowired
    CategoryRepository repository;

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Category findById(Integer id) throws DataNotFoundException {
        return repository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public void save(Category category) {
        repository.save(category);
    }

    @Override
    public void delete(Integer id) {
        try {
            Category category = this.findById(id);
            repository.delete(category);
        } catch (DataNotFoundException e) {
            System.out.println("do nothing");
        }
    }

}
