package com.twu.biblioteca;

import java.util.Scanner;

/**
 * Created by esiow on 15/01/2015.
 */
public class ConsoleInputHandler implements InputHandler {

    private Scanner console = new Scanner(System.in);

    @Override
    public String nextLine() {
        return console.nextLine();
    }

}
