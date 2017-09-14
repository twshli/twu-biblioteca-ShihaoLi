package com.twu.biblioteca.command.handler;

import com.twu.biblioteca.command.ExecResult;
import com.twu.biblioteca.command.State;

/**
 * Created by Shli on 14/09/2017.
 */
public class QuitAppHandler implements CommandHandler {

    @Override
    public ExecResult handle(String param) {
        return new ExecResult(State.QUIT_APP, "");
    }
}
