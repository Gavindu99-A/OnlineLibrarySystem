package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByAvailableCopiesGreaterThan(int count);
    List<Book> findByAuthorIgnoreCase(String author);
    List<Book> findByPublishedYear(int year);

}
