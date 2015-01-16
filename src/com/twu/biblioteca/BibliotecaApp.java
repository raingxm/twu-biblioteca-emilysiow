package com.twu.biblioteca;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BibliotecaApp {


    public static final String WELCOME_MSG = "Welcome to Biblioteca!";
    public static final String MENU_ERROR_MSG = "Select a valid option!";
    public static final String MAIN_MENU_MSG =
            "Main Menu (please select one of the following options by typing its number and pressing ENTER)";
    public static final List<String> MAIN_MENU_OPTIONS =
            Lists.newArrayList("View My Info", "List Books", "Checkout Book", "Return Book", "List Movies", "Checkout Movie");
    public static final String LOGIN_MSG = "You need to login to continue with this action";
    public static final String LIBRARY_NUM_PROMPT = "Please enter your library number (xxx-xxxx): ";
    public static final String PWORD_PROMPT = "Please enter your password: ";
    public static final String LOGIN_ERROR_MSG = "Login failed (incorrect library number or password).";


    public static final String BOOK = "Book";
    public static final String MOVIE = "Movie";

    public static final int SHOW_USER_INFO  = 0;
    public static final int LIST_BOOKS      = 1;
    public static final int CHECKOUT_BOOK   = 2;
    public static final int RETURN_BOOK     = 3;
    public static final int LIST_MOVIES     = 4;
    public static final int CHECKOUT_MOVIE  = 5;

    public static final String DIVIDER = Strings.repeat("-", 100);
    public static final String EXIT_CODE = "quit";
    public static final String CHECKOUT_SUCCESS_MSG = "Thank you! Enjoy the %s";
    public static final String CHECKOUT_FAIL_MSG = "That %s is not available.";
    public static final String RETURN_SUCCESS_MSG = "Thank you for returning the %s.";
    public static final String RETURN_FAIL_MSG = "That is not a valid %s to return.";
    public static final String LIST_HEADER = "%s List";


    private Catalogue<Book> bookCatalogue;
    private Catalogue<Movie> movieCatalogue;
    private UserDatabase userList;
    public User currentUser;

    private InputHandler input;
    private OutputHandler output;

    public BibliotecaApp(InputHandler input, OutputHandler output, Collection<Book> bookList, Collection<Movie> movieList, Collection<User> userList) {
        this.input = input;
        this.output = output;
        this.bookCatalogue = new Catalogue<Book>(bookList);
        this.movieCatalogue = new Catalogue<Movie>(movieList);
        this.userList = new UserDatabase(userList);
        this.currentUser = null;
    }

    public void run() {
        displayStartup();
        runMainMenu();
    }

    void displayStartup() {
        output.println(WELCOME_MSG);
    }

    void runMainMenu() {
        printMainMenu();

        String userInput = input.nextLine();
        while (!userInput.equalsIgnoreCase(EXIT_CODE)) {
            if(isInteger(userInput)) {
                selectMenuOption(Integer.parseInt(userInput));
            } else {
                output.println(MENU_ERROR_MSG);
            }
            printMainMenu();
            userInput = input.nextLine();
        }
    }

    private void printMainMenu() {
        output.println(MAIN_MENU_MSG);
        if (currentUser != null) {
            output.println(String.format("(%d) %s", SHOW_USER_INFO, MAIN_MENU_OPTIONS.get(SHOW_USER_INFO)));
        }
        for(int i=1; i < MAIN_MENU_OPTIONS.size(); i++) {
            output.println(String.format("(%d) %s", i, MAIN_MENU_OPTIONS.get(i)));
        }
    }

    void selectMenuOption(int menuOption) {
        if (menuOption == SHOW_USER_INFO && currentUser != null) {
            printUserInfo();
        } else if(menuOption == LIST_BOOKS) {
            printListing(BOOK);
        } else if(menuOption == CHECKOUT_BOOK) {
            runCheckoutMenu(BOOK);
        } else if(menuOption == RETURN_BOOK) {
            runReturnMenu(BOOK);
        } else if(menuOption == LIST_MOVIES) {
            printListing(MOVIE);
        } else if(menuOption == CHECKOUT_MOVIE) {
            runCheckoutMenu(MOVIE);
        } else {
            output.println(MENU_ERROR_MSG);
        }
    }

    private void printUserInfo() {
        output.println(User.HEADER);
        output.println(DIVIDER);
        output.println(currentUser.printString());
    }

    void runCheckoutMenu(String type) {
        if (userAuthenticate()) {
            output.print(String.format("Enter the title of the %s you wish to check out: ", type.toLowerCase()));

            String userInput = input.nextLine();
            checkoutItem(userInput, type);
        } else {
            output.println(LOGIN_ERROR_MSG);
        }
    }

    void runReturnMenu(String type) {
        if (userAuthenticate()) {
            output.print(String.format("Enter the title of the %s you wish to return: ", type.toLowerCase()));

            String userInput = input.nextLine();
            returnItem(userInput, type);
        } else {
            output.println(LOGIN_ERROR_MSG);
        }
    }
    void checkoutItem(String title, String type) {
        boolean checkoutSuccess = false;

        if (type.matches(BOOK)) {
            checkoutSuccess = bookCatalogue.checkoutItem(title);
        } else if (type.matches(MOVIE)) {
            checkoutSuccess = movieCatalogue.checkoutItem(title);
        }

        if (checkoutSuccess) {
            output.println(String.format(CHECKOUT_SUCCESS_MSG, type.toLowerCase()));
        } else {
            output.println(String.format(CHECKOUT_FAIL_MSG, type.toLowerCase()));
        }
    }

    void returnItem(String title, String type) {
        boolean returnSuccess = false;

        if (type.equalsIgnoreCase(BOOK)) {
            returnSuccess = bookCatalogue.returnItem(title);
        }

        if (returnSuccess) {
            output.println(String.format(RETURN_SUCCESS_MSG, type.toLowerCase()));
        } else {
            output.println(String.format(RETURN_FAIL_MSG, type.toLowerCase()));
        }
    }

    void printListing(String type) {
        output.println(String.format(LIST_HEADER, type));
        if(type.matches(BOOK)) {
            output.println(Book.HEADER);
            output.println(DIVIDER);
            bookCatalogue.printListing(output);
        } else if (type.matches(MOVIE)) {
            output.println(Movie.HEADER);
            output.println(DIVIDER);
            movieCatalogue.printListing(output);
        }
    }

    boolean userAuthenticate() {
        if (currentUser != null) {
            return true;
        } else {
            return userExists();
        }
    }

    private boolean userExists() {
        output.println(LOGIN_MSG);
        output.print(LIBRARY_NUM_PROMPT);
        String userInput = input.nextLine();
        User u = userList.findByLibraryNum(userInput);
        if (u != null) {
            return checkPassword(u);
        }
        return false;
    }

    private boolean checkPassword(User u) {
        String userInput;
        output.print(PWORD_PROMPT);
        userInput = input.nextLine();
        if (u.login(userInput)) {
            currentUser = u;
            return true;
        }
        return false;
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
