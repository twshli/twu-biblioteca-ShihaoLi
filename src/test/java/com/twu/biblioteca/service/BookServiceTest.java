package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Shli on 10/09/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Before
    public void setUp() throws Exception {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(
                new Book("book_no1", "author1", 2012),
                new Book("book_no2", "author2", 2015),
                new Book("book_no3", "author3", 2014)
        ));
    }

    @Test
    public void should_get_all_books_in_the_library() throws Exception {
        List<Book> books = bookService.getAllBooks();

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
        Book book = bookService.getAllBooks().stream()
                .filter(b -> b.getTitle().equals("book_no1"))
                .findFirst().get();

        assertThat(book.getAuthor(), is("author1"));
        assertThat(book.getPublishYear(), is(2012));
    }
}
