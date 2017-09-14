package com.twu.biblioteca.command;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.util.BookInfoCreator;

import java.util.List;

/**
 * Created by Shli on 12/09/2017.
 */
public class CommandConsumer {

    private StateManager stateManager = new StateManager();

    private BookService bookService;

    public CommandConsumer(BookService bookService) {
        this.bookService = bookService;
    }

    public ExecResult exec(String param) {
        switch (stateManager.getState()) {
            case State.INIT_APP:
                return handleInitAppState();

            case State.MAIN_MENU:
                return handleMainMenuState(param);

            case State.CHECKOUT_BOOK:
                return handleCheckoutBookState(param);

            default:
                return new ExecResult("");
        }
    }

    private ExecResult handleInitAppState() {
        return execInitAppCommand();
    }

    private ExecResult handleMainMenuState(String command) {
        switch (command) {
            case Command.LIST_BOOKS:
                return execListBooksCommand();

            case Command.CHECKOUT_BOOK:
                return execCheckoutBookCommand();

            default:
                return new ExecResult(Message.ALERT_SELECT_VALID_OPTION);
        }
    }

    private ExecResult handleCheckoutBookState(String title) {
        if (bookService.checkoutBook(title)) {
            stateManager.setState(State.MAIN_MENU);
            return new ExecResult(Message.ALERT_CHECKOUT_SUCCESS + "\n" + Message.MAIN_MENU);
        } else {
            return new ExecResult(Message.ALERT_CHECKOUT_FAILURE);
        }
    }

    private ExecResult execInitAppCommand() {
        stateManager.setState(State.MAIN_MENU);
        return new ExecResult(Message.MAIN_MENU);
    }

    private ExecResult execListBooksCommand() {
        List<Book> books = bookService.getAllAvailBooks();

        String booksInfo = (books.isEmpty() ? Message.ALERT_NO_AVAIL_BOOKS
                : BookInfoCreator.generate(books));

        return new ExecResult(booksInfo + "\n" + Message.MAIN_MENU);
    }

    private ExecResult execCheckoutBookCommand() {
        stateManager.setState(State.CHECKOUT_BOOK);
        return new ExecResult(Message.ALERT_CHECKOUT);
    }
}
