package com.maids.librarymanagementsystem.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity

@Table(name = "Book")
public class Book extends GenericDomain<Integer> {


    @NotNull
    @Size(min = 5, max = 30)
    @Column(name = "title")
    private String title;

    @NotNull
    @Size(min = 5, max = 30)
    @Column(name = "author")
    private String author;

    @NotNull
    @Min(1950)
    @Column(name = "publication_date")
    private Integer publicationDate;


    @Column(name = "ISBN")
    private Boolean ISBN;

    public Book() {
    }

    public Book(@NotNull @Size(min = 5, max = 30) String title, @NotNull @Size(min = 5, max = 30) String author, @NotNull @Min(1950) Integer publicationDate, Boolean ISBN) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Integer publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Boolean getISBN() {
        return ISBN;
    }

    public void setISBN(Boolean ISBN) {
        this.ISBN = ISBN;
    }
}
