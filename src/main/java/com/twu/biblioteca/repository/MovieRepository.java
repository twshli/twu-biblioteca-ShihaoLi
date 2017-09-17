package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.Movie;

import java.util.List;

public interface MovieRepository {

    void add(Movie movie);

    List<Movie> findAllAvail();

    boolean checkoutByTitle(String title);
}
