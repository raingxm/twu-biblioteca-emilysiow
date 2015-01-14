package com.twu.biblioteca;

/**
 * Created by esiow on 14/01/2015.
 */
public interface Item {
    public boolean matches(String title);

    public String printString();
}