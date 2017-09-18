package com.twu.biblioteca.model;

public class Account {

    private String libraryNumber;
    private String password;

    public Account(String libraryNumber, String password) {
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }
}
