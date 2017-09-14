package com.twu.biblioteca.command;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.util.BookInfoCreator;

import java.util.List;

/**
 * Created by Shli on 12/09/2017.
 */
public class CommandConsumer {

    private BookService bookService;

    public CommandConsumer(BookService bookService) {
        this.bookService = bookService;
    }

    public ExecResult exec(String curState, String param) {
        switch (curState) {
            case State.INIT_APP:
                return handleInitAppState();

            case State.MAIN_MENU:
                return handleMainMenuState(param);

            case State.CHECKOUT_BOOK:
                return handleCheckoutBookState(param);

            default:
                return execQuitAppCommand();
        }
    }

    private ExecResult handleInitAppState() {
        return new ExecResult(State.MAIN_MENU, Message.MAIN_MENU);
    }

    private ExecResult handleMainMenuState(String command) {
        switch (command) {
            case Command.LIST_BOOKS:
                return execListBooksCommand();

            case Command.CHECKOUT_BOOK:
                return execCheckoutBookCommand();

            case Command.QUIT_APP:
                return execQuitAppCommand();

            default:
                return new ExecResult(State.MAIN_MENU, Message.ALERT_SELECT_VALID_OPTION);
        }
    }

    private ExecResult execListBooksCommand() {
        List<Book> books = bookService.getAllAvailBooks();

        String booksInfo = (books.isEmpty() ? Message.ALERT_NO_AVAIL_BOOKS
                : BookInfoCreator.generate(books));

        return new ExecResult(State.MAIN_MENU, booksInfo + "\n" + Message.MAIN_MENU);
    }

    private ExecResult execCheckoutBookCommand() {
        return new ExecResult(State.CHECKOUT_BOOK, Message.ALERT_CHECKOUT);
    }

    private ExecResult execQuitAppCommand() {
        return new ExecResult(State.QUIT_APP, "");
    }

    private ExecResult handleCheckoutBookState(String title) {
        if (bookService.checkoutBook(title)) {
            return new ExecResult(State.MAIN_MENU,Message.ALERT_CHECKOUT_SUCCESS + "\n" + Message.MAIN_MENU);
        } else {
            return new ExecResult(State.CHECKOUT_BOOK, Message.ALERT_CHECKOUT_FAILURE);
        }
    }
}
