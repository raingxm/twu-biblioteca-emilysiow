package com.twu.biblioteca;

/**
 * Created by emilys on 25/12/2014.
 */
public class Book implements Item {
    public static final String HEADER = String.format("%-42s | %-32s | %-12s", "Title", "Author", "Year Published");

    public String title;
    public String author;
    public int yearPublished;

    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    @Override
    public boolean matches(String title) {
        return this.title.equalsIgnoreCase(title);
    }

    @Override
    public String printString() {
        String leftAlignFormat = "%-42s | %-32s | %-4d";
        return String.format(leftAlignFormat, title, author, yearPublished);
    }

    @Override
    public String toString() {
        return this.title;
    }
}
