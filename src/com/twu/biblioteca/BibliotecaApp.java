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
        newBookList.add(createNewBook("Test-Driven Development By Example", "Kent Beck", 2003));
        newBookList.add(createNewBook("The Agile Samurai", "Jonathan Rasmusson", 2010));
        newBookList.add(createNewBook("Head First Java", "Kathy Sierra & Bert Bates", 2005));
        newBookList.add(createNewBook("Don't Make Me Think, Revisited: A Common Sense Approach to Web Usability", "Steve Krug", 2014));
        return newBookList;
    }

    private static Book createNewBook(String title, String author, int yearPublished) {
        return new Book(title, author, yearPublished);
    }
}
