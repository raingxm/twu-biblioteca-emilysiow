package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esiow on 14/01/2015.
 */
public class Library<T extends Item> {
    private List<T> available;
    private List<T> checkedOut;

    public Library(List<T> listing) {
        this.available = listing;
        this.checkedOut = new ArrayList<T>();
    }

    boolean checkoutItem(String title) {
        T item = findTitle(available, title);
        if (item != null) {
            available.remove(item);
            checkedOut.add(item);
            return true;
        }
        return false;
    }

    boolean returnItem(String title) {
        T item = findTitle(checkedOut, title);
        if (item != null) {
            available.add(item);
            checkedOut.remove(item);
            return true;
        }
        return false;
    }

    public void remove(T item) {
        this.available.remove(item);
    }

    public void add(T item) {
        this.available.add(item);
    }

    public boolean isAvailable(String title) {
        return (findTitle(available,title) != null);
    }

    public T findTitle(List<T> listing, String title) {
        T item = null;
        for (T t : listing) {
            if (t.matches(title)) {
                item = t;
            }
        }
        return item;
    }

    public void printListing() {
        for (T t : this.available) {
            t.printString();
        }
    }
}