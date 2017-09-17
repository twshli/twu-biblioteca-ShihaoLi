package com.twu.biblioteca.model;

public class Movie {

    private String title;
    private String director;
    private int publishYear;
    private int rating;

    public Movie(String title, String director, int publishYear, int rating) {
        this.title = title;
        this.director = director;
        this.publishYear = publishYear;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public int getRating() {
        return rating;
    }
}
