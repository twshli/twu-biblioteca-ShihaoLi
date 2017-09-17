package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.Rating;
import com.twu.biblioteca.repository.MovieMemoryRepository;
import com.twu.biblioteca.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class MovieServiceTest {

    private MovieService movieService;

    @Before
    public void setUp() throws Exception {
        MovieRepository movieRepository = new MovieMemoryRepository();
        movieRepository.add(new Movie("movie_1", "director_1", 2016, Rating.ONE));
        movieRepository.add(new Movie("movie_2", "director_2", 2017, Rating.NONE));
        movieRepository.add(new Movie("movie_3", "director_3", 2015, Rating.THREE));

        movieService = new MovieService(movieRepository);
    }

    @Test
    public void should_get_all_available_movies() throws Exception {
        List<Movie> movies = movieService.getAllAvailMovies();

        assertThat(movies.size(), is(3));

        Movie movie = movies.stream()
                .filter(m -> m.getTitle().equals("movie_1"))
                .findFirst().get();

        assertNotNull(movie);
        assertThat(movie.getDirector(), is("director_1"));
        assertThat(movie.getPublishYear(), is(2016));
        assertThat(movie.getRating(), is(Rating.ONE));
    }
}