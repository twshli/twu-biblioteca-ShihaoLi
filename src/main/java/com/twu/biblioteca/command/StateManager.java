package com.twu.biblioteca.command;

/**
 * Created by Shli on 14/09/2017.
 */
public class StateManager {

    private String state = State.INIT_APP;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
