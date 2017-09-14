package com.twu.biblioteca.command;

import com.twu.biblioteca.command.handler.Option;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Shli on 12/09/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommandConsumerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private CommandConsumer consumer;

    @Test
    public void should_enter_main_menu_after_init_the_app() throws Exception {
        ExecResult result = consumer.exec(State.INIT_APP, "");

        assertThat(result.getMessage(), is(Message.MAIN_MENU));
    }

    @Test
    public void should_return_alert_message_when_input_invalid_option_in_main_menu() throws Exception {
        ExecResult result = consumer.exec(State.MAIN_MENU,"xxxx");

        assertThat(result.getMessage(), is(Message.ALERT_SELECT_VALID_OPTION));
    }

    @Test
    public void should_enter_exit_state_when_select_quit_option_in_main_menu() throws Exception {
        ExecResult result = consumer.exec(State.MAIN_MENU, Option.QUIT_APP);

        assertThat(result.getState(), is(State.QUIT_APP));
    }

    @Test
    public void should_return_alert_message_and_main_menu_when_select_list_option_with_no_available_books_in_main_menu() throws Exception {
        when(bookService.getAllAvailBooks()).thenReturn(new ArrayList<>());

        ExecResult result = consumer.exec(State.MAIN_MENU, Option.LIST_BOOKS);

        assertThat(result.getMessage(), is(Message.ALERT_NO_AVAIL_BOOKS + "\n" + Message.MAIN_MENU));
    }

    @Test
    public void should_return_books_info_and_main_menu_when_select_list_option_with_available_books_in_main_menu() throws Exception {
        when(bookService.getAllAvailBooks()).thenReturn(Collections.singletonList(
                new Book("book_1", "author_1", 2012)
        ));

        ExecResult result = consumer.exec(State.MAIN_MENU, Option.LIST_BOOKS);

        String expected = "| book_1 | author_1 | 2012 |\n" + Message.MAIN_MENU;
        assertThat(result.getMessage(), is(expected));
    }

    @Test
    public void should_return_alert_message_when_select_checkout_option_in_main_menu() throws Exception {
        ExecResult result = consumer.exec(State.MAIN_MENU, Option.CHECKOUT_BOOK);

        assertThat(result.getMessage(), is(Message.ALERT_CHECKOUT));
    }

    @Test
    public void should_return_success_message_and_main_menu_after_checkout_a_book() throws Exception {
        when(bookService.checkoutBook("book_1")).thenReturn(true);

        ExecResult result = consumer.exec(State.CHECKOUT_BOOK, "book_1");

        assertThat(result.getMessage(), is(Message.ALERT_CHECKOUT_SUCCESS + "\n" + Message.MAIN_MENU));
    }

    @Test
    public void should_return_alert_message_after_fail_to_checkout_a_book() throws Exception {
        when(bookService.checkoutBook("book_xx")).thenReturn(false);

        ExecResult result = consumer.exec(State.CHECKOUT_BOOK,"book_xx");

        assertThat(result.getMessage(), is(Message.ALERT_CHECKOUT_FAILURE));
    }
}
