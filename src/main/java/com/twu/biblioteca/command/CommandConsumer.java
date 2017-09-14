package com.twu.biblioteca.command;

import com.twu.biblioteca.command.handler.*;
import com.twu.biblioteca.service.BookService;

/**
 * Created by Shli on 12/09/2017.
 */
public class CommandConsumer {

    private BookService bookService;

    public CommandConsumer(BookService bookService) {
        this.bookService = bookService;
    }

    public ExecResult exec(String curState, String param) {
        return dispatch(curState).handle(param);
    }

    private CommandHandler dispatch(String state) {
        switch (state) {
            case State.INIT_APP:
                return new InitAppHandler();

            case State.MAIN_MENU:
                return new MainMenuHandler(bookService);

            case State.CHECKOUT_BOOK:
                return new CheckoutBookHandler(bookService);

            default:
                return new QuitAppHandler();
        }
    }
}
