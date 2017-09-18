package com.twu.biblioteca.command;

public class Message {

    public static final String WELCOME = "Hello, welcome to Biblioteca system!";

    public static final String MAIN_MENU =
            "1. Login\n" +
            "2. List Books\n" +
            "3. Checkout Book\n" +
            "4. Return Book\n" +
            "5. List Movies\n" +
            "6. Checkout Movie\n" +
            "7. Quit";

    public static final String ALERT_SELECT_VALID_OPTION = "Select a valid option!";

    public static final String ALERT_NO_AVAIL_BOOKS = "No available books in library!";

    public static final String ALERT_CHECKOUT_BOOK = "Input title of book to checkout: ";
    public static final String ALERT_CHECKOUT_BOOK_SUCCESS = "Thank you! Enjoy the book.";
    public static final String ALERT_CHECKOUT_BOOK_FAILURE = "That book is not available.";

    public static final String ALERT_RETURN_BOOK = "Input title of book to return: ";
    public static final String ALERT_RETURN_BOOK_SUCCESS = "Thank you for returning the book.";
    public static final String ALERT_RETURN_BOOK_FAILURE = "That is not a valid book to return.";

    public static final String ALERT_NO_AVAIL_MOVIES = "No available movies in library!";

    public static final String ALERT_CHECKOUT_MOVIE = "Input title of movie to checkout: ";
    public static final String ALERT_CHECKOUT_MOVIE_SUCCESS = "Thank you! Enjoy the movie.";
    public static final String ALERT_CHECKOUT_MOVIE_FAILURE = "That movie is not available.";

    public static final String ALERT_LOGIN = "Input (library-number, password) to login: ";
    public static final String ALERT_LOGIN_SUCCESS = "Login successfully!";
    public static final String ALERT_LOGIN_FAILURE = "Fail to login.";
}
