package com.maids.librarymanagementsystem.controller;

import com.maids.librarymanagementsystem.domain.Borrowing;
import com.maids.librarymanagementsystem.log.Log;
import com.maids.librarymanagementsystem.service.BorrowingService;
import com.maids.librarymanagementsystem.utils.model.ActionType;
import com.maids.librarymanagementsystem.utils.model.ResponseObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class BorrowingController {

    private final BorrowingService borrowingService;
    private Map<String, Object> error = new HashMap<>();

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping("borrow/{bookId}/patron/{patronId}")
    @Log(actionType = ActionType.BORROW)
    public ResponseObject insert(@PathVariable Integer bookId, @PathVariable Integer patronId) {
        try {
            Borrowing borrowing = borrowingService.insert(bookId, patronId);
            return borrowing != null ?
                    ResponseObject.ADDED_SUCCESS(borrowing, null) :
                    ResponseObject.ADDING_FAILED(null, null);
        } catch (Exception e) {
            e.printStackTrace();
            error.put("error", e.getMessage());
            return ResponseObject.ADDING_FAILED(null, error);
        }

    }

    @PutMapping("return/{bookId}/patron/{patronId}")
    @Log(actionType = ActionType.RETURN)
    public ResponseObject update(@PathVariable Integer bookId, @PathVariable Integer patronId) {
        try {
            Borrowing borrowing = borrowingService.update(bookId, patronId);
            return borrowing != null ?
                    ResponseObject.UPDATED_SUCCESS(borrowing, null) :
                    ResponseObject.UPDATING_FAILED(null, null);
        } catch (Exception e) {
            e.printStackTrace();
            error.put("error", e.getMessage());
            return ResponseObject.UPDATING_FAILED(null, error);
        }

    }

}
