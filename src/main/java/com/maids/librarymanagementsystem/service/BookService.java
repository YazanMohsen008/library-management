package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.dao.BookDao;
import com.maids.librarymanagementsystem.domain.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends GenericService<BookDao, Book, Integer> {
    @Override
    @Cacheable("cacheManager")
    public List<Book> getAll() {
        return super.getAll();
    }


}
