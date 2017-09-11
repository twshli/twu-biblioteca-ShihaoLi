package com.twu.biblioteca.command;

/**
 * Created by Shli on 12/09/2017.
 */
public class CommandConsumer {

    public ExecResult exec(String command) {
        if (Command.INIT_APP.equals(command)) {
            return new ExecResult(Message.MAIN_MENU);
        }
        return new ExecResult(Message.ALERT_VALID_OPTION);
    }
}
