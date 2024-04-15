package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.dao.BorrowingDao;
import com.maids.librarymanagementsystem.domain.Book;
import com.maids.librarymanagementsystem.domain.Borrowing;
import com.maids.librarymanagementsystem.domain.Patron;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BorrowingService extends GenericService<BorrowingDao, Borrowing, Integer> {

    private final BookService bookService;
    private final PatronService patronService;
    private final BorrowingDao borrowingDao;

    public BorrowingService(BookService bookService, PatronService patronService, BorrowingDao borrowingDao) {
        this.bookService = bookService;
        this.patronService = patronService;
        this.borrowingDao = borrowingDao;
    }

    @Transactional
    public Borrowing insert(Integer bookId, Integer patronId) throws Exception {
        Book book = bookService.getById(bookId);
        Patron patron = patronService.getById(patronId);
        Borrowing borrowing = borrowingDao.findBorrowingByBookId(bookId);
        if (!validateBorrowing(borrowing))
            throw new Exception("This Book is not available");
        borrowing = new Borrowing(book, patron, "Borrowing");
        return super.insert(borrowing);
    }

    @Transactional
    public Borrowing update(Integer bookId, Integer patronId) throws Exception {
        Book book = bookService.getById(bookId);
        Patron patron = patronService.getById(patronId);
        Borrowing borrowing = new Borrowing(book, patron, "Returned");
        return borrowingDao.save(borrowing);
    }

    public Boolean validateBorrowing(Borrowing borrowing) {
        if (borrowing != null && borrowing.getStatus().equals("Borrowing"))
            return false;
            return true;
    }
}
