package com.example.book_manage_system.dao;

import com.example.book_manage_system.common.DataNotFoundException;
import com.example.book_manage_system.entity.Author;
import com.example.book_manage_system.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDao implements BaseDao<Author> {
    @Autowired
    AuthorRepository repository;

    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }

    @Override
    public Author findById(Integer id) throws DataNotFoundException {
        return repository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public void save(Author author) {
        repository.save(author);
    }

    @Override
    public void delete(Integer id) {
        try {
            Author author = this.findById(id);
            repository.delete(author);
        } catch (DataNotFoundException e) {
            System.out.println("do nothing");
        }
    }

}
