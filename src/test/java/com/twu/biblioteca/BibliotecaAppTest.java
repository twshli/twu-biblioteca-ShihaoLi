package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BibliotecaAppTest {

    @Test
    public void should_get_welcome_message() throws Exception {
        assertThat(new BibliotecaApp().welcome(), is("Hello, welcome to Biblioteca system!"));
    }
}