package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;

public class BibliotecaAppTest {

    private StringBuilder expectedOutput;
    private ByteArrayOutputStream output;
    private InputStream input;
    private BibliotecaApp app;

    @Before public void initialize() {
        app = new BibliotecaApp();
        app.initBookList();
        expectedOutput = new StringBuilder();
        output = initSystemOutStream();
    }

    @Test
    public void testBibliotecaStartup() {
        input = initSystemInStream("quit");
        app.main(new String[]{});

        displayStartupMessage();
        displayMainMenu();

        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testMenuOptionListBooks() {
        app.selectMenuOption(BibliotecaApp.LIST_BOOKS);

        displayBookList(generateBookList());

        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testMenuOptionInvalidOption() {
        input = initSystemInStream("-1\nquit");
        app.runMainMenu();

        displayMainMenu();
        displayInvalidOptionMessage();

        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testMenuOptionInvalidInput() {
        input = initSystemInStream("Java java java\nquit");
        app.runMainMenu();

        displayMainMenu();
        displayInvalidOptionMessage();

        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testMenuOptionCheckoutBook() {
        input = initSystemInStream("\nquit");
        app.selectMenuOption(BibliotecaApp.CHECKOUT_BOOK);

        displayCheckoutMenu();
        displayUnsuccessfulCheckoutMessage();


        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testMenuOptionsUntilQuit() {
        input = initSystemInStream("-1\n-1\nquit");
        app.runMainMenu();

        displayMainMenu();
        displayInvalidOptionMessage();
        displayInvalidOptionMessage();

        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testSuccessfulCheckout() {
        List<Book> bookList = generateBookList();
        Book bookToCheckout = bookList.remove(2);

        assertTrue(app.isBookAvailable(bookToCheckout.getTitle()));

        input = initSystemInStream(bookToCheckout.getTitle() + "\n");
        app.runCheckoutMenu();

        displayCheckoutMenu();
        displaySuccessfulCheckoutMessage();

        app.selectMenuOption(BibliotecaApp.LIST_BOOKS);

        displayBookList(bookList);

        assertFalse(app.isBookAvailable(bookToCheckout.getTitle()));
        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testUnsuccessfulCheckout() {
        input = initSystemInStream("Head First C++");
        app.runCheckoutMenu();

        displayCheckoutMenu();
        displayUnsuccessfulCheckoutMessage();

        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testReturnBook() {
        List<Book> bookList = generateBookList();
        Book bookToReturn = bookList.remove(2);

        app.checkoutBook(bookToReturn.getTitle());
        assertFalse(app.isBookAvailable(bookToReturn.getTitle()));
        displaySuccessfulCheckoutMessage();

        app.selectMenuOption(BibliotecaApp.LIST_BOOKS);
        displayBookList(bookList);

        input = initSystemInStream(bookToReturn.getTitle() + "\n");
        app.runReturnMenu();
        bookList.add(bookToReturn);
        displayReturnMenu();

        app.selectMenuOption(BibliotecaApp.LIST_BOOKS);
        displayBookList(bookList);

        assertTrue(app.isBookAvailable(bookToReturn.getTitle()));
        assertEquals(expectedOutput.toString(), output.toString());
    }
    
    // helpers

    private ByteArrayOutputStream initSystemOutStream() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        return baos;
    }

    private ByteArrayInputStream initSystemInStream(String testValue) {
        ByteArrayInputStream in = new ByteArrayInputStream(testValue.getBytes());
        System.setIn(in);
        return in;
    }

    private void displayStartupMessage() {
        expectedOutput.append("Welcome to Biblioteca!\n");
    }

    private void displaySuccessfulCheckoutMessage() {
        expectedOutput.append("Thank you! Enjoy the book\n");
    }

    private void displayUnsuccessfulCheckoutMessage() {
        expectedOutput.append("That book is not available.\n");
    }

    private void displayMainMenu() {
        expectedOutput.append("Main Menu (please select one of the following options by typing its number and pressing ENTER)\n");
        expectedOutput.append("(1) List Books\n");
        expectedOutput.append("(2) Checkout Book\n");
    }

    private void displayInvalidOptionMessage() {
        expectedOutput.append("Select a valid option!\n");
    }

    private void displayCheckoutMenu() {
        expectedOutput.append("Enter the title of the book you wish to check out: \n");
    }

    private void displayReturnMenu() {
        expectedOutput.append("Enter the title of the book you wish to return: \n");
    }

    private void displayBookList(List<Book> bookList) {
        expectedOutput.append("Book List\n");
        expectedOutput.append(String.format("%-42s | %-32s | %-12s\n", "Title", "Author", "Year Published"));

        String leftAlignFormat = "%-42s | %-32s | %-4d\n";
        for (Book book : bookList) {
            expectedOutput.append(String.format(leftAlignFormat, book.getTitle(), book.getAuthor(), book.getYearPublished()));
        }
    }

    private List<Book> generateBookList() {
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book("Test-Driven Development By Example", "Kent Beck", 2003));
        bookList.add(new Book("The Agile Samurai", "Jonathan Rasmusson", 2010));
        bookList.add(new Book("Head First Java", "Kathy Sierra & Bert Bates", 2005));
        bookList.add(new Book("Don't Make Me Think, Revisited", "Steve Krug", 2014));
        return bookList;
    }
}
