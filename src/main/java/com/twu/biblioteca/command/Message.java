package com.twu.biblioteca.command;

public class Message {

    public static final String WELCOME = "Hello, welcome to Biblioteca system!";

    public static final String MAIN_MENU =
            "1. List Books\n" +
            "2. Checkout Book\n" +
            "3. Return Book\n" +
            "4. List Movies\n" +
            "5. Quit";

    public static final String ALERT_SELECT_VALID_OPTION = "Select a valid option!";

    public static final String ALERT_NO_AVAIL_BOOKS = "No available books in library!";

    public static final String ALERT_CHECKOUT_BOOK = "Input title of book to checkout: ";
    public static final String ALERT_CHECKOUT_BOOK_SUCCESS = "Thank you! Enjoy the book!";
    public static final String ALERT_CHECKOUT_BOOK_FAILURE = "That book is not available.";

    public static final String ALERT_RETURN_BOOK = "Input title of book to return: ";
    public static final String ALERT_RETURN_BOOK_SUCCESS = "Thank you for returning the book.";
    public static final String ALERT_RETURN_BOOK_FAILURE = "That is not a valid book to return.";

    public static final String ALERT_NO_AVAIL_MOVIES = "No available movies in library!";
}
