package com.twu.biblioteca;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;

public class BibliotecaAppTest {

    @Test
    public void testBibliotecaStartup() {
        StringBuilder expectedOutput = new StringBuilder();
        displayStartupMessage(expectedOutput);

        ByteArrayOutputStream output = initSystemOutStream();
        BibliotecaApp.displayStartup();

        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testSelectMenuOptionListBooks() {
        StringBuilder expectedOutput = new StringBuilder();
        displayBookList(expectedOutput);

        ByteArrayOutputStream output = initSystemOutStream();
        BibliotecaApp.selectMenuOption(1);

        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testSelectMenuOptionInvalidOption() {
        StringBuilder expectedOutput = new StringBuilder();
        displayInvalidOptionMessage(expectedOutput);

        ByteArrayOutputStream output = initSystemOutStream();
        BibliotecaApp.selectMenuOption(-1);

        assertEquals(expectedOutput.toString(), output.toString());
    }

    @Test
    public void testSelectMenuOptionsUntilQuit() {
        StringBuilder expectedOutput = new StringBuilder();
        displayStartupMessage(expectedOutput);
        displayInvalidOptionMessage(expectedOutput);
        displayBookList(expectedOutput);

        ByteArrayOutputStream output = initSystemOutStream();
        InputStream input = initSystemInStream("-1\n1");
        BibliotecaApp.main(new String[]{});

        assertEquals(expectedOutput.toString(), output.toString());
    }

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
        expectedOutput.append("Main Menu (please select one of the following options by typing its number and pressing ENTER)\n");
        expectedOutput.append("(1) List Books\n");
    }

    private void displayInvalidOptionMessage(StringBuilder expectedOutput) {
        expectedOutput.append("Select a valid option!\n");
    }

    private void displayBookList(StringBuilder expectedOutput) {
        List<Book> bookList = generateBookList();
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
