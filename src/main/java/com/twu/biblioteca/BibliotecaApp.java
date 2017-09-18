package com.twu.biblioteca;

import com.twu.biblioteca.command.ExecResult;
import com.twu.biblioteca.command.Executor;
import com.twu.biblioteca.command.Message;
import com.twu.biblioteca.command.State;
import com.twu.biblioteca.model.Account;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.Rating;
import com.twu.biblioteca.repository.AccountMemoryRepository;
import com.twu.biblioteca.repository.AccountRepository;
import com.twu.biblioteca.repository.BookMemoryRepository;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.repository.MovieMemoryRepository;
import com.twu.biblioteca.repository.MovieRepository;
import com.twu.biblioteca.service.AccountService;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;

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
        AccountRepository accountRepository = initAccountRepository();
        AccountService accountService = new AccountService(accountRepository);

        BookRepository bookRepository = initBookRepository();
        BookService bookService = new BookService(bookRepository);

        MovieRepository movieRepository = initMovieRepository();
        MovieService movieService = new MovieService(movieRepository);

        executor = new Executor(bookService, movieService, accountService);
    }

    private AccountRepository initAccountRepository() {
        AccountRepository accountRepository = new AccountMemoryRepository();

        accountRepository.add(new Account("biblioteca-001", "password1", "name1", "e001@xxx.com", "123456"));
        accountRepository.add(new Account("biblioteca-002", "password2", "name2", "e002@xxx.com", "123457"));

        return accountRepository;
    }

    private BookRepository initBookRepository() {
        BookRepository bookRepository = new BookMemoryRepository();

        bookRepository.add(new Book("book1", "author1", 2010));
        bookRepository.add(new Book("book2", "author2", 2015));
        bookRepository.add(new Book("book3", "author3", 2013));

        return bookRepository;
    }

    private MovieRepository initMovieRepository() {
        MovieRepository movieRepository = new MovieMemoryRepository();

        movieRepository.add(new Movie("movie1", "director1", 2016, Rating.ONE));
        movieRepository.add(new Movie("movie2", "director2", 2017, Rating.NONE));
        movieRepository.add(new Movie("movie3", "director3", 2015, Rating.THREE));

        return movieRepository;
    }

    public static String welcome() {
        return Message.WELCOME;
    }

    public static void main(String[] args) {
        new BibliotecaApp().run();
    }
}
