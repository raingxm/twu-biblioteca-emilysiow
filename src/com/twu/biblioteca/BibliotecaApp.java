package com.twu.biblioteca;
import java.util.List;
import java.util.ArrayList;

public class BibliotecaApp {

    private static List<Book> bookList = initBookList();

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca!");
        System.out.println("Main Menu (please select one of the following options by typing its number and pressing ENTER)");
        System.out.println("(1) List Books");
//        printBookList();
    }

    private static void printBookList() {
        System.out.println("Book List");
        System.out.print(String.format("%-42s | %-32s | %-12s\n", "Title", "Author", "Year Published"));
        String leftAlignFormat = "%-42s | %-32s | %-4d\n";
        for (Book book : bookList) {
            System.out.print(String.format(leftAlignFormat, book.getTitle(), book.getAuthor(), book.getYearPublished()));
        }
    }

    private static List<Book> initBookList() {
        List<Book> newBookList = new ArrayList<Book>();
        newBookList.add(createNewBook("Test-Driven Development By Example", "Kent Beck", 2003));
        newBookList.add(createNewBook("The Agile Samurai", "Jonathan Rasmusson", 2010));
        newBookList.add(createNewBook("Head First Java", "Kathy Sierra & Bert Bates", 2005));
        newBookList.add(createNewBook("Don't Make Me Think, Revisited", "Steve Krug", 2014));
        return newBookList;
    }

    private static Book createNewBook(String title, String author, int yearPublished) {
        return new Book(title, author, yearPublished);
    }
}
