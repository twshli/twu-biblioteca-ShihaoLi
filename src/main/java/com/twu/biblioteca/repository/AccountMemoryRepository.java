package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountMemoryRepository implements AccountRepository {

    private Map<String, Account> accounts = new HashMap<>();

    @Override
    public void add(Account account) {
        accounts.put(account.getUsername(), account);
    }

    @Override
    public boolean existsByUsernameAndPassword(String username, String password) {
        Account account = accounts.get(username);
        return (account != null && account.getPassword().equals(password));
    }
}
