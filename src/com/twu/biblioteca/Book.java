package com.twu.biblioteca;

/**
 * Created by emilys on 25/12/2014.
 */
public class Book {

    private String title;
    private String author;
    private int yearPublished;

    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getYearPublished() {
        return this.yearPublished;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
