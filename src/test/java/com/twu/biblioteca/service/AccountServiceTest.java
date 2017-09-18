package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Account;
import com.twu.biblioteca.repository.AccountMemoryRepository;
import com.twu.biblioteca.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class AccountServiceTest {

    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        AccountRepository accountRepository = new AccountMemoryRepository();
        accountRepository.add(new Account("biblioteca-001", "password1", "name1", "e1@xxx.com", "123456"));
        accountRepository.add(new Account("biblioteca-002", "password2", "name2", "e2@xxx.com", "123457"));

        accountService = new AccountService(accountRepository);
    }

    @Test
    public void should_succeed_to_authenticate_valid_account() throws Exception {
        assertThat(accountService.authenticate("biblioteca-001", "password1"), is(true));
    }

    @Test
    public void should_fail_to_authenticate_invalid_library_number() throws Exception {
        assertThat(accountService.authenticate("biblioteca-xxx", "password1"), is(false));
    }

    @Test
    public void should_fail_to_authenticate_username_with_invalid_password() throws Exception {
        assertThat(accountService.authenticate("biblioteca-001", "passwordxx"), is(false));
    }

    @Test
    public void should_return_account_for_valid_library_number() throws Exception {
        assertNotNull(accountService.getAccount("biblioteca-001"));
    }

    @Test
    public void should_return_null_account_for_invalid_library_number() throws Exception {
        assertNull(accountService.getAccount("biblioteca-xxx"));
    }
}
