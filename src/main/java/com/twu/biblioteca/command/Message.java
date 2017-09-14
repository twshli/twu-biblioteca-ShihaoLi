package com.twu.biblioteca.command;

/**
 * Created by Shli on 12/09/2017.
 */
public class Message {

    public static final String WELCOME = "Hello, welcome to Biblioteca system!";

    public static final String MAIN_MENU = "1. List Books\n"
            + "2. Checkout Book\n"
            + "3. Return Book\n"
            + "4. Quit";

    public static final String ALERT_SELECT_VALID_OPTION = "Select a valid option!";

    public static final String ALERT_NO_AVAIL_BOOKS = "No available books in library!";

    public static final String ALERT_CHECKOUT = "Input title of book to checkout: ";
    public static final String ALERT_CHECKOUT_SUCCESS = "Thank you! Enjoy the book!";
    public static final String ALERT_CHECKOUT_FAILURE = "That book is not available.";

    public static final String ALERT_RETURN = "Input title of book to return: ";
    public static final String ALERT_RETURN_SUCCESS = "Thank you for returning the book.";
    public static final String ALERT_RETURN_FAILURE = "That is not a valid book to return.";
}
