package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.Book;

import java.util.List;

public interface BookRepository {

    void add(Book book);

    List<Book> findAllAvail();

    boolean checkoutByTitle(String title);
    boolean returnByTitle(String title);
}
