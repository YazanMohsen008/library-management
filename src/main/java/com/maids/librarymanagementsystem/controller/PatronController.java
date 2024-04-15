package com.maids.librarymanagementsystem.controller;

import com.maids.librarymanagementsystem.service.PatronService;
import com.maids.librarymanagementsystem.dao.PatronDao;
import com.maids.librarymanagementsystem.domain.Patron;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patrons")
public class PatronController extends GenericController<PatronService, PatronDao, Patron, Integer> {



}
