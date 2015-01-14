package com.twu.biblioteca;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    public static final String BOOK = "book";
    public static final int LIST_BOOKS = 1;
    public static final int CHECKOUT_BOOK = 2;
    public static final int RETURN_BOOK = 3;
    public static final int LIST_MOVIES = 4;
    public static final int CHECKOUT_MOVIE = 5;
    public static final int RETURN_MOVIE = 6;
    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca!";

    private Library<Book> bookLibrary;

    public BibliotecaApp(List<Book> bookList) {
        this.bookLibrary = new Library<Book>(bookList);
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
        System.out.println("(3) Return Book");
//        System.out.println("(4) List Movies");
//        System.out.println("(5) Checkout Movie");
//        System.out.println("(6) Return Movie");

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
            printListing(BOOK);
        } else if(menuOption == CHECKOUT_BOOK) {
            runCheckoutMenu(BOOK);
        } else if(menuOption == RETURN_BOOK) {
            runReturnMenu(BOOK);
        } else {
            System.out.println("Select a valid option!");
        }
    }

    void runCheckoutMenu(String type) {
        System.out.printf("Enter the title of the %s you wish to check out: ", type);
        Scanner console = new Scanner(System.in);
        String userInput = console.nextLine();
        checkoutItem(userInput, type);
    }


    void runReturnMenu(String type) {
        System.out.printf("Enter the title of the %s you wish to return: ", type);
        Scanner console = new Scanner(System.in);
        String userInput = console.nextLine();
        returnItem(userInput, type);
    }

    void checkoutItem(String title, String type) {
        boolean checkoutSuccess = false;

        if (type.matches(BOOK)) {
            checkoutSuccess = bookLibrary.checkoutItem(title);
        }

        if (checkoutSuccess) {
            System.out.printf("Thank you! Enjoy the %s\n", type);
        } else {
            System.out.printf("That %s is not available.\n", type);
        }
    }

    void returnItem(String title, String type) {
        boolean returnSuccess = false;

        if (type.equalsIgnoreCase(BOOK)) {
            returnSuccess = bookLibrary.returnItem(title);
        }

        if (returnSuccess) {
            System.out.printf("Thank you for returning the %s.\n", type);
        } else {
            System.out.printf("That is not a valid %s to return.\n", type);
        }
    }

    void printListing(String type) {
        System.out.printf("%s List\n", type);
        if(type.matches(BOOK)) {
            System.out.print(String.format("%-42s | %-32s | %-12s\n", "Title", "Author", "Year Published"));
            bookLibrary.printListing();
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
