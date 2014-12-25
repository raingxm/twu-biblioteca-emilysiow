package com.twu.biblioteca;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;

public class BibliotecaAppTest {

    @Test
    public void testBibliotecaStartup() {
        List<String> bookList = new ArrayList<String>();
        bookList.add("Test-Driven Development By Example");
        bookList.add("The Agile Samurai");
        bookList.add("Head First Java");
        bookList.add("Don't Make Me Think, Revisited: A Common Sense Approach to Web Usability");

        StringBuilder startupOutput = new StringBuilder();
        startupOutput.append("Welcome to Biblioteca!\n");
        startupOutput.append("Book list:\n");
        for (String book : bookList) {
            startupOutput.append(book + "\n");
        }

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        BibliotecaApp.main(new String[] {});

        assertEquals(startupOutput.toString(), outContent.toString());
    }
}
