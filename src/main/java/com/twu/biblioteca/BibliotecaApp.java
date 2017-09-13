package com.twu.biblioteca;

import com.twu.biblioteca.command.Command;
import com.twu.biblioteca.command.CommandConsumer;
import com.twu.biblioteca.command.ExecResult;
import com.twu.biblioteca.command.Message;

import java.util.Scanner;

public class BibliotecaApp {

    public static String welcome() {
        return Message.WELCOME;
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);

        CommandConsumer consumer =  new CommandConsumer();
        String command = Command.INIT_APP;

        System.out.println(welcome());

        do {
            ExecResult result = consumer.exec(command);
            System.out.println(result.getMessage());

            command = scanner.nextLine().trim();
        } while (!Command.QUIT_APP.equals(command));
    }

    public static void main(String[] args) {
        BibliotecaApp.run();
    }
}
