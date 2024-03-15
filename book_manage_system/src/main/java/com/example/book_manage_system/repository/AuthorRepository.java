package com.example.book_manage_system.repository;

import com.example.book_manage_system.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
