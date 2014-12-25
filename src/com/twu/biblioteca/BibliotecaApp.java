package com.twu.biblioteca;
import java.util.List;
import java.util.ArrayList;

public class BibliotecaApp {

    private static List<Book> bookList = initBookList();

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca!");
        printBookList();
    }

    private static void printBookList() {
        System.out.println("Book list:");
        for (Book book : bookList) {
            System.out.println(book.toString());
        }
    }

    private static List<Book> initBookList() {
        List<Book> newBookList = new ArrayList<Book>();
        newBookList.add(createNewBook("Test-Driven Development By Example"));
        newBookList.add(createNewBook("The Agile Samurai"));
        newBookList.add(createNewBook("Head First Java"));
        newBookList.add(createNewBook("Don't Make Me Think, Revisited: A Common Sense Approach to Web Usability"));
        return newBookList;
    }

    private static Book createNewBook(String title) {
        return new Book(title);
    }
}
