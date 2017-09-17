package com.twu.biblioteca.command;

public class ExecResult {

    private String state;
    private String message;

    public ExecResult(String state, String message) {
        this.state = state;
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }
}
