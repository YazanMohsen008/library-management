package com.maids.librarymanagementsystem.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "Borrowing")
public class Borrowing extends GenericDomain<Integer> {

    @ManyToOne
    Book book;

    @ManyToOne
    Patron patron;

    public Borrowing() {
    }

    public Borrowing(Book book, Patron patron) {
        this.book = book;
        this.patron = patron;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }
}
