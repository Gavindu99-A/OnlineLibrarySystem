package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.BorrowingRecord;
import com.example.demo.model.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowRecordRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.BorrowRequest;
import com.example.demo.request.ReturnRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowRecordService {
    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public BorrowingRecord borrowBook(BorrowRequest borrowRequest) {
        User user = userRepository.findById(borrowRequest.getUserId()).orElseThrow(() -> new RuntimeException("user not found"));
        Book book = bookRepository.findById(borrowRequest.getBookId()).orElseThrow(() -> new RuntimeException("book not found"));
        if(book.getAvailableCopies() <= 0){
            throw new RuntimeException("no copies available at this time");
        }
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setUser(user);
        borrowingRecord.setBook(book);
        borrowRecordRepository.save(borrowingRecord);
        return borrowingRecord;
    }

    public BorrowingRecord returnBook(ReturnRequest returnRequest) {
        BorrowingRecord borrowingRecord = borrowRecordRepository.findById(returnRequest.getBorrowingRecordId()).orElseThrow(() -> new RuntimeException("can't find borrow record"));
        if(borrowingRecord.getReturnDate() != null){
            throw new RuntimeException("book already returned");
        }
        borrowingRecord.setReturnDate(LocalDateTime.now());
        borrowRecordRepository.save(borrowingRecord);
        Book book = borrowingRecord.getBook();
        book.setAvailableCopies(book.getAvailableCopies()+1);
        bookRepository.save(book);
        return borrowingRecord;
    }

    public List<Book> getBorrowedBooksByUser(Long userId) {
        List<BorrowingRecord> borrowingRecordList =  borrowRecordRepository.findByUserId(userId);
        return borrowingRecordList.stream().map(BorrowingRecord::getBook).collect(Collectors.toList());
    }
}
