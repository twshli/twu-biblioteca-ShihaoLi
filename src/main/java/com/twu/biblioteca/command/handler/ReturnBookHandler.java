package com.twu.biblioteca.command.handler;

import com.twu.biblioteca.command.ExecResult;
import com.twu.biblioteca.command.Message;
import com.twu.biblioteca.command.State;
import com.twu.biblioteca.service.BookService;

/**
 * Created by Shli on 15/09/2017.
 */
public class ReturnBookHandler implements CommandHandler {

    private BookService bookService;

    public ReturnBookHandler(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public ExecResult handle(String title) {
        if (bookService.returnBook(title)) {
            return new ExecResult(State.MAIN_MENU, Message.ALERT_RETURN_SUCCESS + "\n" + Message.MAIN_MENU);
        } else {
            return new ExecResult(State.RETURN_BOOK, Message.ALERT_RETURN_FAILURE);
        }
    }
}
