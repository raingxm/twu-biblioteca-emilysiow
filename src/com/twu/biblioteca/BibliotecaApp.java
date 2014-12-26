package com.twu.biblioteca;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    public static final int LIST_BOOKS = 1;
    public static final int CHECKOUT_BOOK = 2;
    public static final int RETURN_BOOK = 3;

    private static List<Book> availableBooks;
    private static List<Book> checkedOutBooks;

    public static void main(String[] args) {
        initBookList();
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
            if(isInteger(userInput)) {
                selectMenuOption(Integer.parseInt(userInput));
            } else {
                System.out.println("Select a valid option!");
            }
            userInput = console.nextLine();
        }
    }

    public static void selectMenuOption(int menuOption) {
        if(menuOption == LIST_BOOKS) {
            printBookList();
        } else if(menuOption >= CHECKOUT_BOOK) {
            runCheckoutMenu();
        } else if(menuOption >= RETURN_BOOK) {
            runReturnMenu();
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

    public static void runReturnMenu() {
        System.out.println("Enter the title of the book you wish to return: ");
        Scanner console = new Scanner(System.in);
        String userInput = console.nextLine();
        returnBook(userInput);
    }

    public static void checkoutBook(String bookTitle) {
        Book book = findBook(availableBooks, bookTitle);
        if (book != null) {
            availableBooks.remove(book);
            checkedOutBooks.add(book);
            System.out.println("Thank you! Enjoy the book");
        } else {
            System.out.println("That book is not available.");
        }
    }

    public static void returnBook(String bookTitle) {
        Book book = findBook(checkedOutBooks, bookTitle);
        if (book != null) {
            availableBooks.add(book);
            checkedOutBooks.remove(book);
        }
    }

    private static Book findBook(List<Book> bookList, String bookTitle) {
        Book book = null;
        for (Book b : bookList) {
            if (b.getTitle().equalsIgnoreCase(bookTitle)) {
                book = b;
            }
        }
        return book;
    }

    public static boolean isBookAvailable(String bookTitle) {
        return (findBook(availableBooks, bookTitle) != null);
    }

    public static void printBookList() {
        System.out.println("Book List");
        System.out.print(String.format("%-42s | %-32s | %-12s\n", "Title", "Author", "Year Published"));
        String leftAlignFormat = "%-42s | %-32s | %-4d\n";
        for (Book book : availableBooks) {
            System.out.print(String.format(leftAlignFormat, book.getTitle(), book.getAuthor(), book.getYearPublished()));
        }
    }

    public static void initBookList() {
        checkedOutBooks = new ArrayList<Book>();
        availableBooks = new ArrayList<Book>();
        availableBooks.add(createNewBook("Test-Driven Development By Example", "Kent Beck", 2003));
        availableBooks.add(createNewBook("The Agile Samurai", "Jonathan Rasmusson", 2010));
        availableBooks.add(createNewBook("Head First Java", "Kathy Sierra & Bert Bates", 2005));
        availableBooks.add(createNewBook("Don't Make Me Think, Revisited", "Steve Krug", 2014));
    }

    private static Book createNewBook(String title, String author, int yearPublished) {
        return new Book(title, author, yearPublished);
    }

    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
