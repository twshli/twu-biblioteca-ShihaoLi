package com.twu.biblioteca.command.handler;

import com.twu.biblioteca.command.ExecResult;
import com.twu.biblioteca.command.Message;
import com.twu.biblioteca.command.State;
import com.twu.biblioteca.service.BookService;

public class CheckoutBookHandler implements CommandHandler {

    private BookService bookService;

    public CheckoutBookHandler(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public ExecResult handle(String title) {
        if (bookService.checkoutBook(title)) {
            return new ExecResult(State.MAIN_MENU, Message.ALERT_CHECKOUT_SUCCESS + "\n" + Message.MAIN_MENU);
        } else {
            return new ExecResult(State.CHECKOUT_BOOK, Message.ALERT_CHECKOUT_FAILURE);
        }
    }
}
