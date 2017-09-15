package com.twu.biblioteca.util;

import com.twu.biblioteca.model.Book;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Shli on 12/09/2017.
 */
public class BookInfoBuilder {

    public static String generate(List<Book> books) {
        return books.stream()
                .map(BookInfoBuilder::generate)
                .collect(Collectors.joining("\n"));
    }

    private static String generate(Book book) {
        return String.format("| %s | %s | %d |",
                book.getTitle(), book.getAuthor(), book.getPublishYear());
    }
}
