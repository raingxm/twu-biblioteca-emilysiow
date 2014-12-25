package com.twu.biblioteca;

/**
 * Created by emilys on 25/12/2014.
 */
public class Book {

    private String title;

    public Book(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
