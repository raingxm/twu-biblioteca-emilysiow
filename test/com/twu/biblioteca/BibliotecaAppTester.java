package com.twu.biblioteca;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by esiow on 14/01/2015.
 */
public class BibliotecaAppTester {

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp(new ConsoleInputHandler(), new ConsoleOutputHandler(), initBookList());
        app.run();
    }

    public static List<Book> initBookList() {
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(createNewBook("Test-Driven Development By Example", "Kent Beck", 2003));
        bookList.add(createNewBook("The Agile Samurai", "Jonathan Rasmusson", 2010));
        bookList.add(createNewBook("Head First Java", "Kathy Sierra & Bert Bates", 2005));
        bookList.add(createNewBook("Don't Make Me Think, Revisited", "Steve Krug", 2014));
        return bookList;
    }

    static Book createNewBook(String title, String author, int yearPublished) {
        return new Book(title, author, yearPublished);
    }
}
