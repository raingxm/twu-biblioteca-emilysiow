package com.twu.biblioteca;

/**
 * Created by esiow on 16/01/2015.
 */
public class User implements Item {
    public static final String HEADER = String.format("%-42s | %-32s | %-12s", "Name", "Email Address", "Phone Number");

    public String name;
    public String email;
    public String phoneNum;
    public String libraryNum;
    private String pword;

    public User(String name, String email, String phoneNum, String libraryNum, String pword) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
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
        String leftAlignFormat = "%-42s | %-32s | %-10s";
        return String.format(leftAlignFormat, name, email, phoneNum);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
