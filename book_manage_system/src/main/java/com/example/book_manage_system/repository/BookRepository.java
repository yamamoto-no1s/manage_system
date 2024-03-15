package com.example.book_manage_system.repository;

import com.example.book_manage_system.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthorId(Integer authorId);

    List<Book> findByCategoryId(Integer categoryId);
}
