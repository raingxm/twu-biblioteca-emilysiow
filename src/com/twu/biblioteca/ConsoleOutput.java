package com.twu.biblioteca;

/**
 * Created by esiow on 15/01/2015.
 */
public class ConsoleOutput implements OutputHandler {


    @Override
    public void println(String str) {
        System.out.println(str);
    }

    @Override
    public void print(String str) {
        System.out.print(str);
    }
}
