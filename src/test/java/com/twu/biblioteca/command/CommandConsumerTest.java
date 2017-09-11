package com.twu.biblioteca.command;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Shli on 12/09/2017.
 */
public class CommandConsumerTest {

    @Test
    public void should_return_main_menu_when_init_the_app() throws Exception {
        ExecResult result = new CommandConsumer().exec(Command.INIT_APP);

        assertThat(result.getMessage(), is(Message.MAIN_MENU));
    }
}