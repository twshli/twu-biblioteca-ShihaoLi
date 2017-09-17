package com.twu.biblioteca.model;

public class Book {

    private String title;
    private String author;
    private int publishYear;

    private boolean isAvail = true;

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

    public boolean isAvail() {
        return isAvail;
    }

    public boolean checkout() {
        if (!isAvail) {
            return false;
        }

        isAvail = false;
        return true;
    }

    public boolean returnBack() {
        if (isAvail) {
            return false;
        }

        isAvail = true;
        return true;
    }
}
