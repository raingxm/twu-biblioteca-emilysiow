package com.twu.biblioteca;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

public class BibliotecaApp {


    public static final String WELCOME_MESSAGE = "Welcome to Biblioteca!";
    public static final String MENU_ERROR_MESSAGE = "Select a valid option!";
    public static final String MAIN_MENU_MESSAGE =
            "Main Menu (please select one of the following options by typing its number and pressing ENTER)";
    public static final List<String> MAIN_MENU_OPTIONS =
            Lists.newArrayList("List Books", "Checkout Book", "Return Book");
    public static final String BOOK = "Book";
    public static final int LIST_BOOKS = 1;
    public static final int CHECKOUT_BOOK = 2;
    public static final int RETURN_BOOK = 3;
    public static final String DIVIDER = Strings.repeat("-", 100);
    public static final String BOOK_HEADER = String.format("%-42s | %-32s | %-12s", "Title", "Author", "Year Published");

    private Catalogue<Book> bookLibrary;
    private InputHandler input;
    private OutputHandler output;

    public BibliotecaApp(InputHandler input, OutputHandler output, Collection<Book> bookList) {
        this.input = input;
        this.output = output;
        this.bookLibrary = new Catalogue<Book>(bookList);
    }

    public void run() {
        displayStartup();
        runMainMenu();
    }

    void displayStartup() {
        output.println(WELCOME_MESSAGE);
    }

    void runMainMenu() {
        output.println(MAIN_MENU_MESSAGE);
        for(int i=0; i < MAIN_MENU_OPTIONS.size(); i++) {
            output.println(String.format("(%d) %s", i+1, MAIN_MENU_OPTIONS.get(i)));
        }

        String userInput = input.nextLine();
        while (!userInput.equalsIgnoreCase("quit")) {
            if(isInteger(userInput)) {
                selectMenuOption(Integer.parseInt(userInput));
            } else {
                output.println(MENU_ERROR_MESSAGE);
            }
            userInput = input.nextLine();
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
            output.println(MENU_ERROR_MESSAGE);
        }
    }

    void runCheckoutMenu(String type) {
        output.print(String.format("Enter the title of the %s you wish to check out: ", type.toLowerCase()));
        
        String userInput = input.nextLine();
        checkoutItem(userInput, type);
    }


    void runReturnMenu(String type) {
        output.print(String.format("Enter the title of the %s you wish to return: ", type.toLowerCase()));
        
        String userInput = input.nextLine();
        returnItem(userInput, type);
    }

    void checkoutItem(String title, String type) {
        boolean checkoutSuccess = false;

        if (type.matches(BOOK)) {
            checkoutSuccess = bookLibrary.checkoutItem(title);
        }

        if (checkoutSuccess) {
            output.println(String.format("Thank you! Enjoy the %s", type.toLowerCase()));
        } else {
            output.println(String.format("That %s is not available.", type.toLowerCase()));
        }
    }

    void returnItem(String title, String type) {
        boolean returnSuccess = false;

        if (type.equalsIgnoreCase(BOOK)) {
            returnSuccess = bookLibrary.returnItem(title);
        }

        if (returnSuccess) {
            output.println(String.format("Thank you for returning the %s.", type.toLowerCase()));
        } else {
            output.println(String.format("That is not a valid %s to return.", type.toLowerCase()));
        }
    }

    void printListing(String type) {
        output.println(String.format("%s List", type));
        if(type.matches(BOOK)) {
            output.println(BOOK_HEADER);
            output.println(DIVIDER);
            bookLibrary.printListing(output);
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
