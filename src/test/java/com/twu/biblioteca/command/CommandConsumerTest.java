package com.twu.biblioteca.command;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

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
    private CommandConsumer consumer = new CommandConsumer();

    @Test
    public void should_return_main_menu_when_init_the_app() throws Exception {
        ExecResult result = consumer.exec(Command.INIT_APP);

        assertThat(result.getMessage(), is(Message.MAIN_MENU));
    }

    @Test
    public void should_return_alert_message_when_input_invalid_option() throws Exception {
        ExecResult result = consumer.exec("xxxx");

        assertThat(result.getMessage(), is(Message.ALERT_SELECT_VALID_OPTION));
    }

    @Test
    public void should_return_alert_message_and_main_menu_when_list_books_with_no_available_books() throws Exception {
        when(bookService.getAllBooks()).thenReturn(new ArrayList<>());

        ExecResult result = consumer.exec(Command.LIST_BOOKS);

        assertThat(result.getMessage(), is(Message.ALERT_NO_AVAIL_BOOKS + "\n" + Message.MAIN_MENU));
    }

    @Test
    public void should_return_books_info_and_main_menu_when_list_books() throws Exception {
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(
                new Book("book_1", "author_1", 2012)
        ));

        ExecResult result = consumer.exec(Command.LIST_BOOKS);

        String expected = "| book_1 | author_1 | 2012 |\n" + Message.MAIN_MENU;
        assertThat(result.getMessage(), is(expected));
    }
}
