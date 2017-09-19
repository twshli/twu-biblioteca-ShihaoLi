package com.twu.biblioteca.command.handler;

import com.twu.biblioteca.command.ExecResult;
import com.twu.biblioteca.command.State;

public class InitAppHandler implements CommandHandler {

    @Override
    public ExecResult handle(String param) {
        return new ExecResult(State.MAIN_MENU, MainMenu.getMenu());
    }
}
