package com.twu.biblioteca.command;

import com.twu.biblioteca.command.handler.CheckoutBookHandler;
import com.twu.biblioteca.command.handler.CommandHandler;
import com.twu.biblioteca.command.handler.InitAppHandler;
import com.twu.biblioteca.command.handler.MainMenuHandler;
import com.twu.biblioteca.command.handler.QuitAppHandler;
import com.twu.biblioteca.command.handler.ReturnBookHandler;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;

public class Executor {

    private BookService bookService;
    private MovieService movieService;

    public Executor(BookService bookService, MovieService movieService) {
        this.bookService = bookService;
        this.movieService = movieService;
    }

    public ExecResult exec(String curState, String param) {
        return dispatch(curState).handle(param);
    }

    private CommandHandler dispatch(String state) {
        switch (state) {
            case State.INIT_APP:
                return new InitAppHandler();

            case State.MAIN_MENU:
                return new MainMenuHandler(bookService, movieService);

            case State.CHECKOUT_BOOK:
                return new CheckoutBookHandler(bookService);

            case State.RETURN_BOOK:
                return new ReturnBookHandler(bookService);

            default:
                return new QuitAppHandler();
        }
    }
}
