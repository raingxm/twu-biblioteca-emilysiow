package com.twu.biblioteca;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by esiow on 14/01/2015.
 */
public class BibliotecaAppTester {
    public static final Book BOOK_1 = createNewBook("Test-Driven Development By Example", "Kent Beck", 2003);
    public static final Book BOOK_2 = createNewBook("The Agile Samurai", "Jonathan Rasmusson", 2010);
    public static final Book BOOK_3 = createNewBook("Head First Java", "Kathy Sierra & Bert Bates", 2005);
    public static final Book BOOK_4 = createNewBook("Don't Make Me Think, Revisited", "Steve Krug", 2014);

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp(new ConsoleInputHandler(), new ConsoleOutputHandler(), initBookList());
        app.run();
    }

    static List<Book> initBookList() {
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(BOOK_1);
        bookList.add(BOOK_2);
        bookList.add(BOOK_3);
        bookList.add(BOOK_4);
        return bookList;
    }

    static Book createNewBook(String title, String author, int yearPublished) {
        return new Book(title, author, yearPublished);
    }
}
