package com.twu.biblioteca.util;

import com.twu.biblioteca.model.Account;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountInfoBuilderTest {

    @Test
    public void should_generate_info_for_account() throws Exception {
        Account account = new Account("biblioteca-001", "password", "name", "xxx@xxx.com", "123456789");

        String accountInfo  = "| biblioteca-001 | name | xxx@xxx.com | 123456789 |";
        assertThat(AccountInfoBuilder.generate(account), is(accountInfo));
    }
}