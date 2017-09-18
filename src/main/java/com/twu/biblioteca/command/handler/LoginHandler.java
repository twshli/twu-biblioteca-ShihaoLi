package com.twu.biblioteca.command.handler;

import com.twu.biblioteca.command.ExecResult;
import com.twu.biblioteca.command.Message;
import com.twu.biblioteca.command.State;
import com.twu.biblioteca.model.Account;
import com.twu.biblioteca.service.AccountService;

public class LoginHandler implements CommandHandler {

    private AccountService accountService;

    public LoginHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public ExecResult handle(String param) {
        Account account = parseAccount(param);

        if (account != null && accountService.authenticate(account.getUsername(), account.getPassword())) {
            return new ExecResult(State.MAIN_MENU, Message.ALERT_LOGIN_SUCCESS + "\n" + Message.MAIN_MENU);
        } else {
            return new ExecResult(State.MAIN_MENU, Message.ALERT_LOGIN_FAILURE + "\n" + Message.MAIN_MENU);
        }
    }

    private Account parseAccount(String param) {
        String[] fields = param.split(",");

        if (fields.length == 2) {
            return new Account(fields[0].trim(), fields[1].trim());
        } else {
            return null;
        }
    }
}
