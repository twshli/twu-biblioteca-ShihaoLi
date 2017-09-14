package com.twu.biblioteca.command.handler;

import com.twu.biblioteca.command.ExecResult;

/**
 * Created by Shli on 14/09/2017.
 */
public interface CommandHandler {
    ExecResult handle(String param);
}
