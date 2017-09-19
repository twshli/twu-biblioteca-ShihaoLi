package com.twu.biblioteca.command.handler;

import com.twu.biblioteca.command.ExecResult;
import com.twu.biblioteca.command.LoginManager;
import com.twu.biblioteca.command.Message;
import com.twu.biblioteca.command.State;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;
import com.twu.biblioteca.util.BookInfoBuilder;
import com.twu.biblioteca.util.MovieInfoBuilder;

import java.util.List;

public class MainMenuHandler implements CommandHandler {

    private BookService bookService;
    private MovieService movieService;

    public MainMenuHandler(BookService bookService, MovieService movieService) {
        this.bookService = bookService;
        this.movieService = movieService;
    }

    @Override
    public ExecResult handle(String command) {
        switch (command) {
            case MenuOption.LOGIN:
                return execLoginCommand();

            case MenuOption.LIST_BOOKS:
                return execListBooksCommand();

            case MenuOption.CHECKOUT_BOOK:
                return execCheckoutBookCommand();

            case MenuOption.RETURN_BOOK:
                return execReturnBookCommand();

            case MenuOption.LIST_MOVIES:
                return execListMoviesCommand();

            case MenuOption.CHECKOUT_MOVIE:
                return execCheckoutMovieCommand();

            case MenuOption.QUIT_APP:
                return execQuitAppCommand();

            default:
                return execInvalidCommand();
        }
    }

    private ExecResult execLoginCommand() {
        if (LoginManager.getInstance().isLoggedIn()) {
            return new ExecResult(State.MAIN_MENU, Message.ALERT_ALREADY_LOGIN + "\n" + MainMenu.getMenu());
        } else {
            return new ExecResult(State.LOGIN, Message.ALERT_LOGIN);
        }
    }

    private ExecResult execListBooksCommand() {
        List<Book> books = bookService.getAllAvailBooks();

        String booksInfo = (books.isEmpty() ? Message.ALERT_NO_AVAIL_BOOKS
                : BookInfoBuilder.generate(books));

        return new ExecResult(State.MAIN_MENU, booksInfo + "\n" + MainMenu.getMenu());
    }

    private ExecResult execCheckoutBookCommand() {
        if (LoginManager.getInstance().isLoggedIn()) {
            return new ExecResult(State.CHECKOUT_BOOK, Message.ALERT_CHECKOUT_BOOK);
        } else {
            return new ExecResult(State.MAIN_MENU, Message.ALERT_NOT_LOGIN + "\n" + MainMenu.getMenu());
        }
    }

    private ExecResult execReturnBookCommand() {
        if (LoginManager.getInstance().isLoggedIn()) {
            return new ExecResult(State.RETURN_BOOK, Message.ALERT_RETURN_BOOK);
        } else {
            return new ExecResult(State.MAIN_MENU, Message.ALERT_NOT_LOGIN + "\n" + MainMenu.getMenu());
        }
    }

    private ExecResult execListMoviesCommand() {
        List<Movie> movies = movieService.getAllAvailMovies();

        String moviesInfo = (movies.isEmpty() ? Message.ALERT_NO_AVAIL_MOVIES
                : MovieInfoBuilder.generate(movies));

        return new ExecResult(State.MAIN_MENU, moviesInfo + "\n" + MainMenu.getMenu());
    }

    private ExecResult execCheckoutMovieCommand() {
        return new ExecResult(State.CHECKOUT_MOVIE, Message.ALERT_CHECKOUT_MOVIE);
    }

    private ExecResult execQuitAppCommand() {
        return new ExecResult(State.QUIT_APP, "");
    }

    private ExecResult execInvalidCommand() {
        return new ExecResult(State.MAIN_MENU, Message.ALERT_SELECT_VALID_OPTION);
    }
}
