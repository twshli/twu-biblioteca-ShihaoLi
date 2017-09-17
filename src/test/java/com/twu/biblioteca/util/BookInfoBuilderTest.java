package com.twu.biblioteca.util;

import com.twu.biblioteca.model.Book;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BookInfoBuilderTest {

    @Test
    public void should_create_books_info_for_book_list() throws Exception {
        List<Book> books = Arrays.asList(
                new Book("book_1", "author_1", 2010),
                new Book("book_2", "author_2", 2014),
                new Book("book_3", "author_3", 2012)
        );

        String booksInfo =
                "| book_1 | author_1 | 2010 |\n" +
                "| book_2 | author_2 | 2014 |\n" +
                "| book_3 | author_3 | 2012 |";

        assertThat(BookInfoBuilder.generate(books), is(booksInfo));
    }
}