package com.example.demo.controller;

import com.example.demo.model.BorrowingRecord;
import com.example.demo.request.BorrowRequest;
import com.example.demo.request.ReturnRequest;
import com.example.demo.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrowRecord")
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    @PostMapping("/borrow")
    public ResponseEntity<BorrowingRecord> borrowBook(@RequestBody BorrowRequest borrowRequest){
        BorrowingRecord borrowingRecordCreated = borrowRecordService.borrowBook(borrowRequest);
        return ResponseEntity.ok(borrowingRecordCreated);
    }

    @PostMapping("/return")
    private ResponseEntity<BorrowingRecord> returnBook(@RequestBody ReturnRequest returnRequest){
        BorrowingRecord borrowingRecordUpdated = borrowRecordService.returnBook(returnRequest);
        return ResponseEntity.ok(borrowingRecordUpdated);
    }
}
