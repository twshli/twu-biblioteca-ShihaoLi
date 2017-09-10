package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.Book;

import java.util.List;

/**
 * Created by Shli on 10/09/2017.
 */
public interface BookRepository {

    void add(Book book);
    List<Book> findAdd();
}
