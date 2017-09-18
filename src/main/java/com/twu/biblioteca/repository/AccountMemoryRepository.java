package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountMemoryRepository implements AccountRepository {

    private Map<String, Account> accounts = new HashMap<>();

    @Override
    public void add(Account account) {
        accounts.put(account.getLibraryNumber(), account);
    }

    @Override
    public boolean existsByLibraryNumberAndPassword(String libraryNumber, String password) {
        Account account = accounts.get(libraryNumber);
        return (account != null && account.getPassword().equals(password));
    }
}
