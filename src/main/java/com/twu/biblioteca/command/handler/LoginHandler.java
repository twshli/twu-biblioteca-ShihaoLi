package com.twu.biblioteca.command.handler;

import com.twu.biblioteca.command.ExecResult;
import com.twu.biblioteca.command.LoginManager;
import com.twu.biblioteca.command.Message;
import com.twu.biblioteca.command.State;
import com.twu.biblioteca.service.AccountService;

public class LoginHandler implements CommandHandler {

    private AccountService accountService;

    public LoginHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public ExecResult handle(String account) {
        String fields[] = account.split(",");

        if (fields.length == 2) {
            String libraryNumber = fields[0].trim();
            String password = fields[1].trim();

            if (accountService.authenticate(libraryNumber, password)) {
                LoginManager.getInstance().setLogin(libraryNumber);
                return new ExecResult(State.MAIN_MENU, Message.ALERT_LOGIN_SUCCESS + "\n" + Message.MAIN_MENU);
            }
        }

        return new ExecResult(State.MAIN_MENU, Message.ALERT_LOGIN_FAILURE + "\n" + Message.MAIN_MENU);
    }
}
