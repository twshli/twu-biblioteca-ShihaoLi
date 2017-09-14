package com.twu.biblioteca;

import com.twu.biblioteca.command.Command;
import com.twu.biblioteca.command.CommandConsumer;
import com.twu.biblioteca.command.ExecResult;
import com.twu.biblioteca.command.Message;
import com.twu.biblioteca.repository.BookMemoryRepository;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.service.BookService;

import java.util.Scanner;

public class BibliotecaApp {

    private CommandConsumer consumer;

    public void run() {
        Scanner scanner = new Scanner(System.in);

        String command = Command.INIT_APP;

        init();
        System.out.println(welcome());

        do {
            ExecResult result = consumer.exec(command);
            System.out.println(result.getMessage());

            command = scanner.nextLine().trim();
        } while (!Command.QUIT_APP.equals(command));
    }

    private void init() {
        BookRepository bookRepository = new BookMemoryRepository();
        BookService bookService = new BookService(bookRepository);

        consumer = new CommandConsumer(bookService);
    }


    public static String welcome() {
        return Message.WELCOME;
    }

    public static void main(String[] args) {
        new BibliotecaApp().run();
    }
}
