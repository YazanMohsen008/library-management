package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.dao.PatronDao;
import com.maids.librarymanagementsystem.domain.Patron;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService extends GenericService<PatronDao, Patron, Integer> {
    @Override
    @Cacheable("cacheManager")
    public List<Patron> getAll() {
        return super.getAll();
    }

}
