package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.repository.BookMemoryRepository;
import com.twu.biblioteca.repository.BookRepository;

import java.util.List;

/**
 * Created by Shli on 10/09/2017.
 */
public class BookService {

    private BookRepository bookRepository = new BookMemoryRepository();

    public void addBook(Book book) {
        bookRepository.add(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAdd();
    }
}
