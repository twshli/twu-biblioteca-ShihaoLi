package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookMemoryRepository implements BookRepository {

    private Map<String, Book> books = new HashMap<>();

    @Override
    public void add(Book book) {
        books.put(book.getTitle(), book);
    }

    @Override
    public List<Book> findAllAvail() {
        return books.values().stream()
                .filter(Book::isAvail)
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkoutByTitle(String title) {
        return (books.containsKey(title)
                && books.get(title).checkout());
    }

    @Override
    public boolean returnByTitle(String title) {
        return (books.containsKey(title)
                && books.get(title).returnBack());
    }
}
