package com.example.book_manage_system.service;

import com.example.book_manage_system.common.DataNotFoundException;
import com.example.book_manage_system.dao.BaseDao;
import com.example.book_manage_system.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements BaseService<Author> {
    @Autowired
    private BaseDao<Author> authorDao;

    @Override
    public List<Author> findAll() {
        return authorDao.findAll();
    }

    @Override
    public Author findById(Integer id) throws DataNotFoundException {
        return authorDao.findById(id);
    }

    @Override
    public void save(Author author) {
        authorDao.save(author);
    }

    @Override
    public void delete(Integer id) {
        authorDao.delete(id);
    }
}
