package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Account;
import com.twu.biblioteca.repository.AccountMemoryRepository;
import com.twu.biblioteca.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountServiceTest {

    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        AccountRepository accountRepository = new AccountMemoryRepository();
        accountRepository.add(new Account("user1", "password1"));
        accountRepository.add(new Account("user2", "password2"));

        accountService = new AccountService(accountRepository);
    }

    @Test
    public void should_succeed_to_authenticate_valid_username_and_password() throws Exception {
        assertThat(accountService.authenticate("user1", "password1"), is(true));
    }

    @Test
    public void should_fail_to_authenticate_invalid_username() throws Exception {
        assertThat(accountService.authenticate("userxx", "password1"), is(false));
    }

    @Test
    public void should_fail_to_authenticate_username_with_invalid_password() throws Exception {
        assertThat(accountService.authenticate("user1", "passwordxx"), is(false));
    }
}