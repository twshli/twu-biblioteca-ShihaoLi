package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.Movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieMemoryRepository implements MovieRepository {

    private Map<String, Movie> movies = new HashMap<>();

    @Override
    public void add(Movie movie) {
        movies.put(movie.getTitle(), movie);
    }

    @Override
    public List<Movie> findAllAvail() {
        return movies.values().stream()
                .filter(Movie::isAvail)
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkoutByTitle(String title) {
        return (movies.containsKey(title)
                && movies.get(title).checkout());
    }
}
