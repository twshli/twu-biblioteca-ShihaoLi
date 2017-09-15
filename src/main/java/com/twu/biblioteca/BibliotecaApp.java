package com.twu.biblioteca;

import com.twu.biblioteca.command.ExecResult;
import com.twu.biblioteca.command.Executor;
import com.twu.biblioteca.command.Message;
import com.twu.biblioteca.command.State;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.repository.BookMemoryRepository;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.service.BookService;

import java.util.Scanner;

public class BibliotecaApp {

    private Executor executor;

    public void run() {
        Scanner scanner = new Scanner(System.in);

        String command = "";
        ExecResult result = new ExecResult(State.INIT_APP, "");

        init();
        System.out.println(welcome());

        do {
            result = executor.exec(result.getState(), command);
            if (result.getState().equals(State.QUIT_APP)) {
                break;
            }
            System.out.println(result.getMessage());

            command = scanner.nextLine().trim();
        } while (true);
    }

    private void init() {
        BookRepository bookRepository = initRepository();
        BookService bookService = new BookService(bookRepository);

        executor = new Executor(bookService);
    }

    private BookRepository initRepository() {
        BookRepository bookRepository = new BookMemoryRepository();

        bookRepository.add(new Book("book1", "author1", 2010));
        bookRepository.add(new Book("book2", "author2", 2015));
        bookRepository.add(new Book("book3", "author3", 2013));

        return bookRepository;
    }

    public static String welcome() {
        return Message.WELCOME;
    }

    public static void main(String[] args) {
        new BibliotecaApp().run();
    }
}
