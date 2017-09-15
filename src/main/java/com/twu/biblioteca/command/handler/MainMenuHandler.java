package com.twu.biblioteca.command.handler;

import com.twu.biblioteca.command.ExecResult;
import com.twu.biblioteca.command.Message;
import com.twu.biblioteca.command.State;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.util.BookInfoBuilder;

import java.util.List;

/**
 * Created by Shli on 14/09/2017.
 */
public class MainMenuHandler implements CommandHandler {

    private BookService bookService;

    public MainMenuHandler(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public ExecResult handle(String command) {
        switch (command) {
            case MenuOption.LIST_BOOKS:
                return execListBooksCommand();

            case MenuOption.CHECKOUT_BOOK:
                return execCheckoutBookCommand();

            case MenuOption.RETURN_BOOK:
                return execReturnBookCommand();

            case MenuOption.QUIT_APP:
                return execQuitAppCommand();

            default:
                return execInvalidCommand();
        }
    }

    private ExecResult execListBooksCommand() {
        List<Book> books = bookService.getAllAvailBooks();

        String booksInfo = (books.isEmpty() ? Message.ALERT_NO_AVAIL_BOOKS
                : BookInfoBuilder.generate(books));

        return new ExecResult(State.MAIN_MENU, booksInfo + "\n" + Message.MAIN_MENU);
    }

    private ExecResult execCheckoutBookCommand() {
        return new ExecResult(State.CHECKOUT_BOOK, Message.ALERT_CHECKOUT);
    }

    private ExecResult execReturnBookCommand() {
        return new ExecResult(State.RETURN_BOOK, Message.ALERT_RETURN);
    }

    private ExecResult execQuitAppCommand() {
        return new ExecResult(State.QUIT_APP, "");
    }

    private ExecResult execInvalidCommand() {
        return new ExecResult(State.MAIN_MENU, Message.ALERT_SELECT_VALID_OPTION);
    }
}
