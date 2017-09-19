package com.twu.biblioteca.command.handler;

import com.twu.biblioteca.command.LoginManager;
import com.twu.biblioteca.command.Message;

public class MainMenu {

    public static String getMenu() {
        return (LoginManager.getInstance().isLoggedIn() ?
                Message.MAIN_MENU_FULL : Message.MAIN_MENU_PART);
    }
}
