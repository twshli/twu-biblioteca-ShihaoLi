package com.twu.biblioteca;

import com.twu.biblioteca.command.Message;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BibliotecaAppTest {

    @Test
    public void should_get_welcome_message() throws Exception {
        assertThat(BibliotecaApp.welcome(), is(Message.WELCOME));
    }
}