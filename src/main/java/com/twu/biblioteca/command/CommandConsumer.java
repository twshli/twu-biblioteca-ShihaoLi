package com.twu.biblioteca.command;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.util.BookInfoCreator;

import java.util.List;

/**
 * Created by Shli on 12/09/2017.
 */
public class CommandConsumer {

    private BookService bookService = new BookService();

    public ExecResult exec(String command) {
        switch (command) {
            case Command.INIT_APP:
                return execInitAppCommand();

            case Command.LIST_BOOKS:
                return execListBooksCommand();

            default:
                return new ExecResult(Message.ALERT_SELECT_VALID_OPTION);
        }
    }

    private ExecResult execInitAppCommand() {
        return new ExecResult(Message.MAIN_MENU);
    }

    private ExecResult execListBooksCommand() {
        List<Book> books = bookService.getAllBooks();

        String booksInfo = (books.isEmpty() ? Message.ALERT_NO_AVAIL_BOOKS
                : BookInfoCreator.generate(books));

        return new ExecResult(booksInfo + "\n" + Message.MAIN_MENU);
    }
}
