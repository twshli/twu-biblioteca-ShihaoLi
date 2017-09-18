package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.Account;

public interface AccountRepository {

    void add(Account account);

    boolean existsByLibraryNumberAndPassword(String libraryNumber, String password);
}
