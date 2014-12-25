package com.twu.biblioteca;
import java.util.List;
import java.util.ArrayList;

public class BibliotecaApp {

    private static List<String> bookList = new ArrayList<String>();

    public static void main(String[] args) {
        bookList.add("Test-Driven Development By Example");
        bookList.add("The Agile Samurai");
        bookList.add("Head First Java");
        bookList.add("Don't Make Me Think, Revisited: A Common Sense Approach to Web Usability");

        System.out.println("Welcome to Biblioteca!");
        System.out.println("Book list:");
        for (String book : bookList) {
            System.out.println(book);
        }
    }
}
