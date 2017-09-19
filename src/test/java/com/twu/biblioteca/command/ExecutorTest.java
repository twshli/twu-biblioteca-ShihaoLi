package com.twu.biblioteca.command;

import com.twu.biblioteca.command.handler.MenuOption;
import com.twu.biblioteca.model.Account;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.Rating;
import com.twu.biblioteca.service.AccountService;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;
import org.junit.After;
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

@RunWith(MockitoJUnitRunner.class)
public class ExecutorTest {

    @Mock
    private BookService bookService;
    @Mock
    private MovieService movieService;
    @Mock
    private AccountService accountService;

    @InjectMocks
    private Executor executor;

    @After
    public void tearDown() throws Exception {
        LoginManagerTestUtil.clearLogin();
    }

    @Test
    public void should_enter_main_menu_after_init_the_app() throws Exception {
        ExecResult result = executor.exec(State.INIT_APP, "");

        assertThat(result.getState(), is(State.MAIN_MENU));
        assertThat(isMainMenuContained(result), is(true));
    }

    @Test
    public void should_return_alert_message_when_input_invalid_option_in_main_menu() throws Exception {
        ExecResult result = executor.exec(State.MAIN_MENU,"xxxx");

        assertThat(result.getState(), is(State.MAIN_MENU));
        assertThat(result.getMessage(), is(Message.ALERT_SELECT_VALID_OPTION));
    }

    @Test
    public void should_enter_exit_state_when_select_quit_option_in_main_menu() throws Exception {
        ExecResult result = executor.exec(State.MAIN_MENU, MenuOption.QUIT_APP);

        assertThat(result.getState(), is(State.QUIT_APP));
    }

    @Test
    public void should_return_alert_message_and_main_menu_when_select_list_option_with_no_available_books_in_main_menu() throws Exception {
        when(bookService.getAllAvailBooks()).thenReturn(new ArrayList<>());

        ExecResult result = executor.exec(State.MAIN_MENU, MenuOption.LIST_BOOKS);

        assertThat(result.getState(), is(State.MAIN_MENU));
        assertThat(result.getMessage().contains(Message.ALERT_NO_AVAIL_BOOKS), is(true));
        assertThat(isMainMenuContained(result), is(true));
    }

    @Test
    public void should_return_books_info_and_main_menu_when_select_list_option_with_available_books_in_main_menu() throws Exception {
        when(bookService.getAllAvailBooks()).thenReturn(Collections.singletonList(
                new Book("book_1", "author_1", 2012)
        ));

        ExecResult result = executor.exec(State.MAIN_MENU, MenuOption.LIST_BOOKS);

        String bookInfo = "| book_1 | author_1 | 2012 |";
        assertThat(result.getState(), is(State.MAIN_MENU));
        assertThat(result.getMessage().contains(bookInfo), is(true));
        assertThat(isMainMenuContained(result), is(true));
    }

    @Test
    public void should_return_alert_message_when_select_checkout_option_in_main_menu() throws Exception {
        LoginManagerTestUtil.assumeLoginWithLibraryNumber("biblioteca-001");

        ExecResult result = executor.exec(State.MAIN_MENU, MenuOption.CHECKOUT_BOOK);

        assertThat(result.getState(), is(State.CHECKOUT_BOOK));
        assertThat(result.getMessage(), is(Message.ALERT_CHECKOUT_BOOK));
    }

    @Test
    public void should_return_alert_message_and_main_menu_when_select_checkout_option_without_login() throws Exception {
        ExecResult result = executor.exec(State.MAIN_MENU, MenuOption.CHECKOUT_BOOK);

        assertThat(result.getState(), is(State.MAIN_MENU));
        assertThat(result.getMessage().contains(Message.ALERT_NOT_LOGIN), is(true));
        assertThat(isMainMenuContained(result), is(true));
    }

    @Test
    public void should_return_success_message_and_main_menu_after_checkout_a_book() throws Exception {
        when(bookService.checkoutBook("book_1")).thenReturn(true);

        ExecResult result = executor.exec(State.CHECKOUT_BOOK, "book_1");

        assertThat(result.getState(), is(State.MAIN_MENU));
        assertThat(result.getMessage().contains(Message.ALERT_CHECKOUT_BOOK_SUCCESS), is(true));
        assertThat(isMainMenuContained(result), is(true));
    }

    @Test
    public void should_return_alert_message_after_fail_to_checkout_a_book() throws Exception {
        when(bookService.checkoutBook("book_xx")).thenReturn(false);

        ExecResult result = executor.exec(State.CHECKOUT_BOOK,"book_xx");

        assertThat(result.getState(), is(State.CHECKOUT_BOOK));
        assertThat(result.getMessage(), is(Message.ALERT_CHECKOUT_BOOK_FAILURE));
    }

    @Test
    public void should_return_alert_message_when_select_return_option_in_main_menu() throws Exception {
        LoginManagerTestUtil.assumeLoginWithLibraryNumber("biblioteca-001");

        ExecResult result = executor.exec(State.MAIN_MENU, MenuOption.RETURN_BOOK);

        assertThat(result.getState(), is(State.RETURN_BOOK));
        assertThat(result.getMessage(), is(Message.ALERT_RETURN_BOOK));
    }

    @Test
    public void should_return_alert_message_and_main_menu_when_select_return_option_without_login() throws Exception {
        ExecResult result = executor.exec(State.MAIN_MENU, MenuOption.RETURN_BOOK);

        assertThat(result.getState(), is(State.MAIN_MENU));
        assertThat(result.getMessage().contains(Message.ALERT_NOT_LOGIN), is(true));
        assertThat(isMainMenuContained(result), is(true));
    }

    @Test
    public void should_return_success_message_and_main_menu_after_return_checkout_book() throws Exception {
        when(bookService.returnBook("book_1")).thenReturn(true);

        ExecResult result = executor.exec(State.RETURN_BOOK, "book_1");

        assertThat(result.getState(), is(State.MAIN_MENU));
        assertThat(result.getMessage().contains(Message.ALERT_RETURN_BOOK_SUCCESS), is(true));
        assertThat(isMainMenuContained(result), is(true));
    }

    @Test
    public void should_return_alert_message_after_fail_to_return_book() throws Exception {
        when(bookService.returnBook("book_1")).thenReturn(false);

        ExecResult result = executor.exec(State.RETURN_BOOK, "book_1");

        assertThat(result.getState(), is(State.RETURN_BOOK));
        assertThat(result.getMessage(), is(Message.ALERT_RETURN_BOOK_FAILURE));
    }

    @Test
    public void should_return_alert_message_and_main_menu_when_select_list_movies_with_no_available_movies_in_main_menu() throws Exception {
        when(movieService.getAllAvailMovies()).thenReturn(new ArrayList<>());

        ExecResult result = executor.exec(State.MAIN_MENU, MenuOption.LIST_MOVIES);

        assertThat(result.getState(), is(State.MAIN_MENU));
        assertThat(result.getMessage().contains(Message.ALERT_NO_AVAIL_MOVIES), is(true));
        assertThat(isMainMenuContained(result), is(true));
    }

    @Test
    public void should_return_movies_info_and_main_menu_when_select_list_movies_with_available_movies_in_main_menu() throws Exception {
        when(movieService.getAllAvailMovies()).thenReturn(Collections.singletonList(
                new Movie("movie_1", "director_1", 2016, Rating.ONE)
        ));

        ExecResult result = executor.exec(State.MAIN_MENU, MenuOption.LIST_MOVIES);

        String movieInfo = "| movie_1 | director_1 | 2016 | 1 |";
        assertThat(result.getState(), is(State.MAIN_MENU));
        assertThat(result.getMessage().contains(movieInfo), is(true));
        assertThat(isMainMenuContained(result), is(true));
    }

    @Test
    public void should_return_alert_message_when_select_checkout_movie_in_main_menu() throws Exception {
        ExecResult result = executor.exec(State.MAIN_MENU, MenuOption.CHECKOUT_MOVIE);

        assertThat(result.getState(), is(State.CHECKOUT_MOVIE));
        assertThat(result.getMessage(), is(Message.ALERT_CHECKOUT_MOVIE));
    }

    @Test
    public void should_return_success_message_and_main_menu_after_checkout_a_movie() throws Exception {
        when(movieService.checkoutMovie("movie_1")).thenReturn(true);

        ExecResult result = executor.exec(State.CHECKOUT_MOVIE, "movie_1");

        assertThat(result.getState(), is(State.MAIN_MENU));
        assertThat(result.getMessage().contains(Message.ALERT_CHECKOUT_MOVIE_SUCCESS), is(true));
        assertThat(isMainMenuContained(result), is(true));
    }

    @Test
    public void should_return_alert_message_after_fail_to_checkout_a_movie() throws Exception {
        when(movieService.checkoutMovie("movie_1")).thenReturn(false);

        ExecResult result = executor.exec(State.CHECKOUT_MOVIE, "movie_1");

        assertThat(result.getState(), is(State.CHECKOUT_MOVIE));
        assertThat(result.getMessage(), is(Message.ALERT_CHECKOUT_MOVIE_FAILURE));
    }

    @Test
    public void should_return_alert_message_when_select_login_in_main_menu() throws Exception {
        ExecResult result = executor.exec(State.MAIN_MENU, MenuOption.LOGIN);

        assertThat(result.getState(), is(State.LOGIN));
        assertThat(result.getMessage(), is(Message.ALERT_LOGIN));
    }

    @Test
    public void should_return_alert_message_and_main_menu_when_select_login_with_already_login() throws Exception {
        LoginManagerTestUtil.assumeLoginWithLibraryNumber("biblioteca-001");

        ExecResult result = executor.exec(State.MAIN_MENU, MenuOption.LOGIN);

        assertThat(result.getState(), is(State.MAIN_MENU));
        assertThat(result.getMessage().contains(Message.ALERT_ALREADY_LOGIN), is(true));
        assertThat(isMainMenuContained(result), is(true));
    }

    @Test
    public void should_return_success_message_and_main_menu_after_login() throws Exception {
        when(accountService.authenticate("biblioteca-001", "password")).thenReturn(true);

        ExecResult result = executor.exec(State.LOGIN, "biblioteca-001, password");

        assertThat(LoginManager.getInstance().isLoggedIn(), is(true));
        assertThat(result.getState(), is(State.MAIN_MENU));
        assertThat(result.getMessage().contains(Message.ALERT_LOGIN_SUCCESS), is(true));
        assertThat(isMainMenuContained(result), is(true));
    }

    @Test
    public void should_return_alert_message_and_main_menu_after_fail_to_login() throws Exception {
        when(accountService.authenticate("biblioteca-001", "password")).thenReturn(false);

        ExecResult result = executor.exec(State.LOGIN, "biblioteca-001, password");

        assertThat(LoginManager.getInstance().isLoggedIn(), is(false));
        assertThat(result.getState(), is(State.MAIN_MENU));
        assertThat(result.getMessage().contains(Message.ALERT_LOGIN_FAILURE), is(true));
        assertThat(isMainMenuContained(result), is(true));
    }

    @Test
    public void should_return_alert_message_and_main_menu_after_input_account_with_invalid_format() throws Exception {
        ExecResult result = executor.exec(State.LOGIN, "biblioteca-001 password");

        assertThat(LoginManager.getInstance().isLoggedIn(), is(false));
        assertThat(result.getState(), is(State.MAIN_MENU));
        assertThat(result.getMessage().contains(Message.ALERT_LOGIN_FAILURE), is(true));
        assertThat(isMainMenuContained(result), is(true));
    }

    @Test
    public void should_return_message_contains_full_main_menu_after_enter_main_menu_with_login() throws Exception {
        LoginManagerTestUtil.assumeLoginWithLibraryNumber("biblioteca-001");

        ExecResult result = executor.exec(State.INIT_APP, "");

        assertThat(result.getMessage().contains(Message.MAIN_MENU_FULL), is(true));
    }

    @Test
    public void should_return_message_contains_main_menu_without_user_info_option_after_enter_main_menu_without_login() throws Exception {
        ExecResult result = executor.exec(State.INIT_APP, "");

        assertThat(result.getMessage().contains(Message.MAIN_MENU_PART), is(true));
    }

    @Test
    public void should_return_user_info_and_main_menu_when_select_user_info_option_with_login() throws Exception {
        LoginManagerTestUtil.assumeLoginWithLibraryNumber("biblioteca-001");
        when(accountService.getAccount("biblioteca-001")).thenReturn(
                new Account("biblioteca-001", "password", "name", "xxx@xxx.com", "123456")
        );

        ExecResult result = executor.exec(State.MAIN_MENU, MenuOption.USER_INFO);

        String accountInfo = "| biblioteca-001 | name | xxx@xxx.com | 123456 |";
        assertThat(result.getState(), is(State.MAIN_MENU));
        assertThat(result.getMessage().contains(accountInfo), is(true));
        assertThat(isMainMenuContained(result), is(true));
    }

    @Test
    public void should_return_alert_invalid_option_message_when_select_user_info_option_without_login() throws Exception {
        ExecResult result = executor.exec(State.MAIN_MENU, MenuOption.USER_INFO);

        assertThat(result.getMessage(), is(Message.ALERT_SELECT_VALID_OPTION));
    }

    private boolean isMainMenuContained(ExecResult result) {
        return (result.getMessage().contains(Message.MAIN_MENU_PART)
                || result.getMessage().contains(Message.MAIN_MENU_FULL));
    }
}
