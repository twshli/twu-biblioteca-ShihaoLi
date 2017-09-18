package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Account;
import com.twu.biblioteca.repository.AccountRepository;

public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean authenticate(String libraryNumber, String password) {
        return accountRepository.existsByLibraryNumberAndPassword(libraryNumber, password);
    }

    public Account getAccount(String libraryNumber) {
        return accountRepository.findByLibraryNumber(libraryNumber);
    }
}
