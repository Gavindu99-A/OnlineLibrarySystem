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
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setUser(user);
        borrowingRecord.setBook(book);
        borrowRecordRepository.save(borrowingRecord);
        return borrowingRecord;
    }

    public BorrowingRecord returnBook(ReturnRequest returnRequest) {
        BorrowingRecord borrowingRecord = borrowRecordRepository.findById(returnRequest.getBorrowingRecordId()).orElseThrow(() -> new RuntimeException("can't find borrow record"));
        borrowingRecord.setReturnDate(LocalDateTime.now());
        borrowRecordRepository.save(borrowingRecord);
        return borrowingRecord;
    }
}
