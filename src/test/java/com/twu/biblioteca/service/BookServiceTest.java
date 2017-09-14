package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.repository.BookMemoryRepository;
import com.twu.biblioteca.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Shli on 10/09/2017.
 */
public class BookServiceTest {

    private BookService bookService;

    @Before
    public void setUp() throws Exception {
        BookRepository bookRepository = new BookMemoryRepository();
        bookRepository.add(new Book("book_no1", "author1", 2012));
        bookRepository.add(new Book("book_no2", "author2", 2015));
        bookRepository.add(new Book("book_no3", "author3", 2014));

        bookService = new BookService(bookRepository);
    }

    @Test
    public void should_get_all_available_books_in_the_library() throws Exception {
        List<Book> books = bookService.getAllAvailBooks();

        assertThat(books.size(), is(3));

        List<String> titles = books.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());

        assertThat(titles.contains("book_no1"), is(true));
        assertThat(titles.contains("book_no2"), is(true));
        assertThat(titles.contains("book_no3"), is(true));
    }

    @Test
    public void should_get_details_of_all_books_in_the_library() throws Exception {
        Book book = bookService.getAllAvailBooks().stream()
                .filter(b -> b.getTitle().equals("book_no1"))
                .findFirst().get();

        assertThat(book.getAuthor(), is("author1"));
        assertThat(book.getPublishYear(), is(2012));
    }

    @Test
    public void should_checkout_an_available_book() throws Exception {
        assertThat(bookService.checkoutBook("book_no1"), is(true));
        assertThat(bookService.getAllAvailBooks().size(), is(2));
    }

    @Test
    public void should_fail_to_checkout_unexisted_book() throws Exception {
        assertThat(bookService.checkoutBook("book_xx"), is(false));
        assertThat(bookService.getAllAvailBooks().size(), is(3));
    }

    @Test
    public void should_fail_to_checkout_unavailable_book() throws Exception {
        bookService.checkoutBook("book_no1");

        assertThat(bookService.checkoutBook("book_no1"), is(false));
        assertThat(bookService.getAllAvailBooks().size(), is(2));
    }

    @Test
    public void should_succeed_to_return_book_checked_out() throws Exception {
        bookService.checkoutBook("book_no1");

        assertThat(bookService.returnBook("book_no1"), is(true));
        assertThat(bookService.getAllAvailBooks().size(), is(3));
    }

    @Test
    public void should_fail_to_return_unexisted_book() throws Exception {
        assertThat(bookService.returnBook("book_xx"), is(false));
        assertThat(bookService.getAllAvailBooks().size(), is(3));
    }

    @Test
    public void should_fail_to_return_available_book() throws Exception {
        assertThat(bookService.returnBook("book_no1"), is(false));
        assertThat(bookService.getAllAvailBooks().size(), is(3));
    }
}
