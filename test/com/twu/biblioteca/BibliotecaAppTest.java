package com.twu.biblioteca;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;

public class BibliotecaAppTest {

    @Test
    public void testBibliotecaStartup() {
        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Welcome to Biblioteca!\n");
        expectedOutput.append("Main Menu (please select one of the following options by typing its number and pressing ENTER)\n");
        expectedOutput.append("(1) List Books\n");

        ByteArrayOutputStream outContent = initSystemOutStream();
        BibliotecaApp.displayStartup();

        assertEquals(expectedOutput.toString(), outContent.toString());
    }

    @Test
    public void testSelectMenuOptionListBooks() {
        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append(printBookList());

        ByteArrayOutputStream outContent = initSystemOutStream();
        BibliotecaApp.selectMenuOption(1);

        assertEquals(expectedOutput.toString(), outContent.toString());
    }

    @Test
    public void testSelectMenuOptionInvalidOption() {
        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Select a valid option!\n");

        ByteArrayOutputStream outContent = initSystemOutStream();
        BibliotecaApp.selectMenuOption(-1);

        assertEquals(expectedOutput.toString(), outContent.toString());
    }


    private ByteArrayOutputStream initSystemOutStream() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        return outContent;
    }

    private String printBookList() {
        List<Book> bookList = generateBookList();
        StringBuilder output = new StringBuilder();
        output.append("Book List\n");
        output.append(String.format("%-42s | %-32s | %-12s\n", "Title", "Author", "Year Published"));

        String leftAlignFormat = "%-42s | %-32s | %-4d\n";
        for (Book book : bookList) {
            output.append(String.format(leftAlignFormat, book.getTitle(), book.getAuthor(), book.getYearPublished()));
        }
        return output.toString();
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
