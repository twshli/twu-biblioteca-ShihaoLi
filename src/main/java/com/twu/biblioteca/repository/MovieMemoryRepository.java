package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieMemoryRepository implements MovieRepository {

    private Map<String, Movie> movies = new HashMap<>();

    @Override
    public void add(Movie movie) {
        movies.put(movie.getTitle(), movie);
    }

    @Override
    public List<Movie> findAllAvail() {
        return new ArrayList<>(movies.values());
    }
}
