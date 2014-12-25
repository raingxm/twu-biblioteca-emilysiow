package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
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
        displayStartupMessage(expectedOutput);
        displayMainMenu(expectedOutput);

        input = initSystemInStream("quit");
        app.main(new String[]{});

        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testSelectMenuOptionListBooks() {
        displayBookList(expectedOutput, generateBookList());

        app.selectMenuOption(BibliotecaApp.LIST_BOOKS);

        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testSelectMenuOptionInvalidOption() {
        displayMainMenu(expectedOutput);
        displayInvalidOptionMessage(expectedOutput);
        input = initSystemInStream("-1\nquit");
        app.runMainMenu();

        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testSelectMenuOptionInvalidInput() {
        displayMainMenu(expectedOutput);
        displayInvalidOptionMessage(expectedOutput);
        input = initSystemInStream("Java java java\nquit");
        app.runMainMenu();

        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testSelectMenuOptionCheckoutBook() {
        displayCheckoutMenu(expectedOutput);
        displayUnsuccessfulCheckoutMessage(expectedOutput);

        input = initSystemInStream("\nquit");
        app.selectMenuOption(BibliotecaApp.CHECKOUT_BOOK);

        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testSelectMenuOptionsUntilQuit() {
        displayMainMenu(expectedOutput);
        displayInvalidOptionMessage(expectedOutput);
        displayInvalidOptionMessage(expectedOutput);

        input = initSystemInStream("-1\n-1\nquit");
        app.runMainMenu();

        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testSuccessfulCheckout() {
        displayCheckoutMenu(expectedOutput);
        List<Book> bookList = generateBookList();
        Book bookToCheckout = bookList.remove(2);
        displaySuccessfulCheckoutMessage(expectedOutput);
        displayBookList(expectedOutput, bookList);

        assertTrue(app.isBookAvailable(bookToCheckout.getTitle()));

        input = initSystemInStream(bookToCheckout.getTitle() + "\n");
        app.runCheckoutMenu();
        app.selectMenuOption(BibliotecaApp.LIST_BOOKS);

        assertTrue(!app.isBookAvailable(bookToCheckout.getTitle()));
        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testUnsuccessfulCheckout() {
        displayCheckoutMenu(expectedOutput);
        displayUnsuccessfulCheckoutMessage(expectedOutput);

        input = initSystemInStream("Head First C++");
        app.runCheckoutMenu();

        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testReturnBook() {
        List<Book> bookList = generateBookList();
        displayBookList(expectedOutput, bookList);
        Book bookToReturn = bookList.remove(2);

        app.checkoutBook(bookToReturn.getTitle());
        assertTrue(!app.isBookAvailable(bookToReturn.getTitle()));

        input = initSystemInStream(bookToReturn.getTitle() + "\n");
        app.runReturnMenu();

        assertTrue(app.isBookAvailable(bookToCheckout.getTitle()));
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

    private void displayStartupMessage(StringBuilder expectedOutput) {
        expectedOutput.append("Welcome to Biblioteca!\n");
    }

    private void displaySuccessfulCheckoutMessage(StringBuilder expectedOutput) {
        expectedOutput.append("Thank you! Enjoy the book\n");
    }

    private void displayUnsuccessfulCheckoutMessage(StringBuilder expectedOutput) {
        expectedOutput.append("That book is not available.\n");
    }

    private void displayMainMenu(StringBuilder expectedOutput) {
        expectedOutput.append("Main Menu (please select one of the following options by typing its number and pressing ENTER)\n");
        expectedOutput.append("(1) List Books\n");
        expectedOutput.append("(2) Checkout Book\n");
    }

    private void displayInvalidOptionMessage(StringBuilder expectedOutput) {
        expectedOutput.append("Select a valid option!\n");
    }

    private void displayCheckoutMenu(StringBuilder expectedOutput) {
        expectedOutput.append("Enter the title of the book you wish to check out: \n");
    }

    private void displayBookList(StringBuilder expectedOutput, List<Book> bookList) {
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
