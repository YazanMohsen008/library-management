package com.maids.librarymanagementsystem.dao;


import com.maids.librarymanagementsystem.domain.Borrowing;
import org.springframework.data.repository.query.Param;

public interface BorrowingDao extends GenericDao<Borrowing,Integer> {

    Borrowing findBorrowingByBookId(@Param("bookId") Integer bookId);

}
