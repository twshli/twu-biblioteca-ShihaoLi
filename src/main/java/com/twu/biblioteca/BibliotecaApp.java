package com.twu.biblioteca;

public class BibliotecaApp {

    public String welcome() {
        return "Hello, welcome to Biblioteca system!";
    }

    public static void main(String[] args) {
        System.out.println(new BibliotecaApp().welcome());
    }
}
