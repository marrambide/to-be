package com.project.tobe.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("book")
public class Book {

    @Id
    private String id;

    private String name;
    private String author;
    private String genre;
    private Boolean finished;
    private Number pages;
    private Number currPage;
    private Number rating;
    private Date finishedDate;

    public Book (String id, String name, String author, String genre, Number pages) {
        super();
        this.id = id;
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.pages = pages;
        this.finished = false;
        this.currPage = 0;
        this.rating = 0;

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        this.finishedDate = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Number getPages() {
        return pages;
    }

    public void setPages(Number pages) {
        this.pages = pages;
    }

    public Number getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Number curr_page) {
        this.currPage = curr_page;
    }

    public Number getRating() {
        return rating;
    }

    public void setRating(Number rating) {
        this.rating = rating;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finished_date) {
        this.finishedDate = finished_date;
    }
}
