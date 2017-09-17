package com.twu.biblioteca.util;

import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.Rating;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MovieInfoBuilderTest {

    @Test
    public void should_build_movies_info_for_list_of_movies() throws Exception {
        List<Movie> movies = Arrays.asList(
                new Movie("movie_1", "director_1", 2016, Rating.ONE),
                new Movie("movie_2", "director_2", 2017, Rating.NONE),
                new Movie("movie_3", "director_3", 2015, Rating.THREE)
        );

        String moviesInfo =
                "| movie_1 | director_1 | 2016 | 1 |\n" +
                "| movie_2 | director_2 | 2017 | unrated |\n" +
                "| movie_3 | director_3 | 2015 | 3 |";

        assertThat(MovieInfoBuilder.generate(movies), is(moviesInfo));
    }
}