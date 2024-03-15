package com.example.book_manage_system.service;

import com.example.book_manage_system.common.DataNotFoundException;
import com.example.book_manage_system.dao.BookDao;
import com.example.book_manage_system.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements BaseService<Book> {
    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book findById(Integer id) throws DataNotFoundException {
        return bookDao.findById(id);
    }

    public List<Book> findByAuthorId(Integer authorId) throws DataNotFoundException {
        return bookDao.findByAuthorId(authorId);
    }

    public List<Book> findByCategoryId(Integer categoryId) throws DataNotFoundException {
        return bookDao.findByCategoryId(categoryId);
    }

    @Override
    public void save(Book book) {
        bookDao.save(book);
    }

    @Override
    public void delete(Integer id) {
        bookDao.delete(id);
    }
}
