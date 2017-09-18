package com.twu.biblioteca.command;

public class LoginManager {

    private static LoginManager instance = new LoginManager();

    private String libraryNumber;
    private boolean loggedIn = false;

    private LoginManager() {

    }

    public static LoginManager getInstance() {
        return instance;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLogin(String libraryNumber) {
        this.libraryNumber = libraryNumber;
        this.loggedIn = true;
    }

    public void setLogout() {
        this.libraryNumber = null;
        this.loggedIn = false;
    }
}
