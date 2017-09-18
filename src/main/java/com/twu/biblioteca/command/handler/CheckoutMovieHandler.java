package com.twu.biblioteca.command.handler;

import com.twu.biblioteca.command.ExecResult;
import com.twu.biblioteca.command.Message;
import com.twu.biblioteca.command.State;
import com.twu.biblioteca.service.MovieService;

/**
 * Created by Shli on 17/09/2017.
 */
public class CheckoutMovieHandler implements CommandHandler {

    private MovieService movieService;

    public CheckoutMovieHandler(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public ExecResult handle(String title) {
        if (movieService.checkoutMovie(title)) {
            return new ExecResult(State.MAIN_MENU, Message.ALERT_CHECKOUT_MOVIE_SUCCESS + "\n" + Message.MAIN_MENU);
        } else {
            return new ExecResult(State.CHECKOUT_MOVIE, Message.ALERT_CHECKOUT_MOVIE_FAILURE);
        }
    }
}
