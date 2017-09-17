package com.twu.biblioteca.command.handler;

import com.twu.biblioteca.command.ExecResult;

public interface CommandHandler {

    ExecResult handle(String param);
}
