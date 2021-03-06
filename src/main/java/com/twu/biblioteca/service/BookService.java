package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.repository.BookRepository;

import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllAvailBooks() {
        return bookRepository.findAllAvail();
    }

    public boolean checkoutBook(String title) {
        return bookRepository.checkoutByTitle(title);
    }

    public boolean returnBook(String title) {
        return bookRepository.returnByTitle(title);
    }
}
