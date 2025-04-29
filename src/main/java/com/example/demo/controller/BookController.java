package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.request.AuthorRequest;
import com.example.demo.request.YearRequest;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/register")
    public ResponseEntity<Book> registerBook(@RequestBody Book book){
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.ok(createdBook);
    }

    @GetMapping("/allBooks")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/availableBooks")
    public List<Book> getAvailableBooks(){
        List<Book> books = bookService.getAvailableBooks();
        return books;
    }

    @GetMapping("/author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestBody AuthorRequest authorRequest){
        List<Book> books = bookService.getBooksByAuthor(authorRequest);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/year")
    public ResponseEntity<List<Book>> getBooksByPublishedTear(@RequestBody YearRequest yearRequest){
        List<Book> books = bookService.getBooksByYear(yearRequest);
        return ResponseEntity.ok(books);
    }

}
