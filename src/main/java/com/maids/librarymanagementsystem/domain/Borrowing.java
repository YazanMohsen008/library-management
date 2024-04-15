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

    String status;
    public Borrowing() {
    }

    public Borrowing(Book book, Patron patron,String status) {
        this.book = book;
        this.patron = patron;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
