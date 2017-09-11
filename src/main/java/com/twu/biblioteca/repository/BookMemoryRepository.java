package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.Book;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Shli on 10/09/2017.
 */
public class BookMemoryRepository implements BookRepository {

    private List<Book> books = new LinkedList<>();

    @Override
    public void add(Book book) {
        books.add(book);
    }

    @Override
    public List<Book> findAll() {
        return books;
    }
}
