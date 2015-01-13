package com.twu.biblioteca;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    public static final int LIST_BOOKS = 1;
    public static final int CHECKOUT_BOOK = 2;
    public static final int RETURN_BOOK = 3;
    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca!";

    private List<Book> availableBooks;
    private List<Book> checkedOutBooks;

    public BibliotecaApp(List<Book> bookList) {
        this.availableBooks = bookList;
        this.checkedOutBooks = new ArrayList<Book>();
    }

    public void run() {
        displayStartup();
        runMainMenu();
    }

    void displayStartup() {
        System.out.println(WELCOME_MESSAGE);
    }

    void runMainMenu() {
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

    void selectMenuOption(int menuOption) {
        if(menuOption == LIST_BOOKS) {
            printBookList();
        } else if(menuOption == CHECKOUT_BOOK) {
            runCheckoutMenu();
        } else if(menuOption == RETURN_BOOK) {
            runReturnMenu();
        } else {
            System.out.println("Select a valid option!");
        }
    }

    void runCheckoutMenu() {
        System.out.println("Enter the title of the book you wish to check out: ");
        Scanner console = new Scanner(System.in);
        String userInput = console.nextLine();
        checkoutBook(userInput);
    }

    void runReturnMenu() {
        System.out.println("Enter the title of the book you wish to return: ");
        Scanner console = new Scanner(System.in);
        String userInput = console.nextLine();
        returnBook(userInput);
    }

    void checkoutBook(String bookTitle) {
        Book book = findBook(availableBooks, bookTitle);
        if (book != null) {
            availableBooks.remove(book);
            checkedOutBooks.add(book);
            System.out.println("Thank you! Enjoy the book");
        } else {
            System.out.println("That book is not available.");
        }
    }

    void returnBook(String bookTitle) {
        Book book = findBook(checkedOutBooks, bookTitle);
        if (book != null) {
            availableBooks.add(book);
            checkedOutBooks.remove(book);
            System.out.println("Thank you for returning the book.");
        } else {
            System.out.println("That is not a valid book to return.");
        }
    }

    Book findBook(List<Book> bookList, String bookTitle) {
        Book book = null;
        for (Book b : bookList) {
            if (b.title.equalsIgnoreCase(bookTitle)) {
                book = b;
            }
        }
        return book;
    }

    boolean isBookAvailable(String bookTitle) {
        return (findBook(availableBooks, bookTitle) != null);
    }

    void printBookList() {
        System.out.println("Book List");
        System.out.print(String.format("%-42s | %-32s | %-12s\n", "Title", "Author", "Year Published"));
        String leftAlignFormat = "%-42s | %-32s | %-4d\n";
        for (Book book : this.availableBooks) {
            System.out.print(String.format(leftAlignFormat, book.title, book.author, book.yearPublished));
        }
    }

    static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
