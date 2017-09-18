package com.twu.biblioteca.command;

import com.twu.biblioteca.command.handler.CheckoutBookHandler;
import com.twu.biblioteca.command.handler.CheckoutMovieHandler;
import com.twu.biblioteca.command.handler.CommandHandler;
import com.twu.biblioteca.command.handler.InitAppHandler;
import com.twu.biblioteca.command.handler.LoginHandler;
import com.twu.biblioteca.command.handler.MainMenuHandler;
import com.twu.biblioteca.command.handler.QuitAppHandler;
import com.twu.biblioteca.command.handler.ReturnBookHandler;
import com.twu.biblioteca.service.AccountService;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;

public class Executor {

    private BookService bookService;
    private MovieService movieService;
    private AccountService accountService;

    public Executor(BookService bookService, MovieService movieService, AccountService accountService) {
        this.bookService = bookService;
        this.movieService = movieService;
        this.accountService = accountService;
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

            case State.LOGIN:
                return new LoginHandler(accountService);

            case State.CHECKOUT_BOOK:
                return new CheckoutBookHandler(bookService);

            case State.RETURN_BOOK:
                return new ReturnBookHandler(bookService);

            case State.CHECKOUT_MOVIE:
                return new CheckoutMovieHandler(movieService);

            default:
                return new QuitAppHandler();
        }
    }
}
