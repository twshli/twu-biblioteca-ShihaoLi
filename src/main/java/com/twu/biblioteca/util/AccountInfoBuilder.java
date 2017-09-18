package com.twu.biblioteca.util;

import com.twu.biblioteca.model.Account;

public class AccountInfoBuilder {

    public static String generate(Account account) {
        return String.format("| %s | %s | %s | %s |",
                account.getLibraryNumber(), account.getName(),
                account.getEmail(), account.getPhone());
    }
}
