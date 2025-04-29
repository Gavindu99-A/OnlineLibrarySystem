package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.request.AuthorRequest;
import com.example.demo.request.YearRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book createBook(Book book) {
        Book newBook = bookRepository.save(book);
        return newBook;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailableCopiesGreaterThan(0);
    }

    public List<Book> getBooksByAuthor(AuthorRequest authorRequest) {
        return bookRepository.findByAuthorIgnoreCase(authorRequest.getAuthor());
    }

    public List<Book> getBooksByYear(YearRequest yearRequest) {
        return bookRepository.findByPublishedYear(yearRequest.getPublishedYear());
    }
}
