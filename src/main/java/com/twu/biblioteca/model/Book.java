package com.twu.biblioteca.model;

/**
 * Created by Shli on 10/09/2017.
 */
public class Book {
    private String title;
    private String author;
    private int publishYear;

    public Book(String title, String author, int publishYear) {
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishYear() {
        return publishYear;
    }
}
