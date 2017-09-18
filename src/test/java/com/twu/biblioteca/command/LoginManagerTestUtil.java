package com.twu.biblioteca.command;

public class LoginManagerTestUtil {

    public static void assumeLoginWithLibraryNumber(String libraryNumber) {
        LoginManager.getInstance().setLogin(libraryNumber);
    }

    public static void clearLogin() {
        LoginManager.getInstance().setLogout();
    }
}
