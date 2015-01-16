package com.twu.biblioteca;

/**
 * Created by esiow on 16/01/2015.
 */
public class Movie implements Item {
    public static final String HEADER = String.format("%-42s | %-32s | %-4s | %-12s", "Name", "Director", "Year", "Movie Rating");
    private static final int UNRATED = 0;

    public String name;
    public String director;
    public int yearReleased;
    public int rating;

    public Movie(String name, String director, int yearReleased) {
        this.name = name;
        this.director = director;
        this.yearReleased = yearReleased;
        this.rating = UNRATED;
    }

    public Movie(String name, String director, int yearReleased, int rating) {
        this.name = name;
        this.director = director;
        this.yearReleased = yearReleased;
        this.rating = rating;
    }

    @Override
     public boolean matches(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    @Override
    public String printString() {
        String leftAlignFormat = "%-42s | %-32s | %-4d | %-7s";
        String printRating = Integer.toString(rating);
        if (rating == UNRATED) {
            printRating = "unrated";
        }
        return String.format(leftAlignFormat, name, director, yearReleased, printRating);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
