package com.twu.biblioteca;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    public static int LIST_BOOKS = 1;
    public static int CHECKOUT_BOOK = 2;

    private static List<Book> bookList;

    public static void main(String[] args) {
        displayStartup();
        runMainMenu();
    }

    public static void displayStartup() {
        System.out.println("Welcome to Biblioteca!");
    }


    public static void runMainMenu() {
        System.out.println("Main Menu (please select one of the following options by typing its number and pressing ENTER)");
        System.out.println("(1) List Books");
        System.out.println("(2) Checkout Book");

        Scanner console = new Scanner(System.in);
        String userInput = console.nextLine();
        while (!userInput.equalsIgnoreCase("quit")) {
            selectMenuOption(Integer.parseInt(userInput));
            userInput = console.nextLine();
        }
    }

    public static void selectMenuOption(int menuOption) {
        if(menuOption == LIST_BOOKS) {
            printBookList();
        } else if(menuOption == CHECKOUT_BOOK) {
            runCheckoutMenu();
        } else {
            System.out.println("Select a valid option!");
        }
    }

    public static void runCheckoutMenu() {
        System.out.println("Enter the title of the book you wish to check out: ");
        Scanner console = new Scanner(System.in);
        String userInput = console.nextLine();
        checkoutBook(userInput);
    }

    public static void checkoutBook(String bookTitle) {
        Book book = null;
        for (Book b : bookList) {
            if (b.getTitle().equalsIgnoreCase(bookTitle)) {
                book = b;
            }
        }
        if (book != null) {
            bookList.remove(book);
            System.out.println("Thank you! Enjoy the book");
        }
    }

    public static void printBookList() {
        System.out.println("Book List");
        System.out.print(String.format("%-42s | %-32s | %-12s\n", "Title", "Author", "Year Published"));
        String leftAlignFormat = "%-42s | %-32s | %-4d\n";
        for (Book book : bookList) {
            System.out.print(String.format(leftAlignFormat, book.getTitle(), book.getAuthor(), book.getYearPublished()));
        }
    }

    public static List<Book> initBookList() {
        bookList = new ArrayList<Book>();
        bookList.add(createNewBook("Test-Driven Development By Example", "Kent Beck", 2003));
        bookList.add(createNewBook("The Agile Samurai", "Jonathan Rasmusson", 2010));
        bookList.add(createNewBook("Head First Java", "Kathy Sierra & Bert Bates", 2005));
        bookList.add(createNewBook("Don't Make Me Think, Revisited", "Steve Krug", 2014));
        return bookList;
    }

    private static Book createNewBook(String title, String author, int yearPublished) {
        return new Book(title, author, yearPublished);
    }
}
