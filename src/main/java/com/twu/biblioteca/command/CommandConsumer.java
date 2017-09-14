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

    public ExecResult exec(String command) {
        switch (stateManager.getState()) {
            case State.INIT_APP:
                return handleInitAppState();

            case State.MAIN_MENU:
                return handleMainMenuState(command);

            case State.CHECKOUT_BOOK:
                return handleCheckoutBookState(command);

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

    private ExecResult handleCheckoutBookState(String command) {
        return null;
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
        return new ExecResult(Message.ALERT_CHECKOUT);
    }
}
