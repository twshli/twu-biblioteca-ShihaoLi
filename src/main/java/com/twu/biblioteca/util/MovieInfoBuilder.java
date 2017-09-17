package com.twu.biblioteca.util;

import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.Rating;

import java.util.List;
import java.util.stream.Collectors;

public class MovieInfoBuilder {

    public static String generate(List<Movie> movies) {
        return movies.stream()
                .map(MovieInfoBuilder::generate)
                .collect(Collectors.joining("\n"));
    }

    private static String generate(Movie movie) {
        return String.format("| %s | %s | %d | %s |",
                movie.getTitle(), movie.getDirector(), movie.getPublishYear(),
                generateMovieRatingInfo(movie.getRating()));
    }

    private static String generateMovieRatingInfo(int rating) {
        return (rating == Rating.NONE ? "unrated" : String.valueOf(rating));
    }
}
