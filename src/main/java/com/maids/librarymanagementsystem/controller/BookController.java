package com.maids.librarymanagementsystem.controller;

import com.maids.librarymanagementsystem.dao.BookDao;
import com.maids.librarymanagementsystem.domain.Book;
import com.maids.librarymanagementsystem.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController extends GenericController<BookService, BookDao, Book, Integer> {



}
