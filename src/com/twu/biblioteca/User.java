package com.twu.biblioteca;

/**
 * Created by esiow on 16/01/2015.
 */
public class User implements Item {

    public String libraryNum;
    private String pword;

    public User(String libraryNum, String pword) {
        this.libraryNum = libraryNum;
        this.pword = pword;
    }

    public boolean login(String pword) {
        return this.pword.matches(pword);
    }

    @Override
    public boolean matches(String libraryNum) {
        return this.libraryNum.equalsIgnoreCase(libraryNum);
    }

    @Override
    public String printString() {
        return this.libraryNum;
    }
}
