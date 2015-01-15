package com.twu.biblioteca;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BibliotecaAppTest {
    private TestInputHandler input = new TestInputHandler();
    private TestOutputHandler output = new TestOutputHandler();
    private Collection<Book> bookList  = BibliotecaAppTester.initBookList();
    private Collection<Movie> movieList = BibliotecaAppTester.initMovieList();

    private BibliotecaApp app;

    @Before
    public void initialize() {
        app = new BibliotecaApp(input, output, bookList, movieList);
    }

    @Test
    public void testBibliotecaStartup() {
        input.add(app.EXIT_CODE);
        app.run();

        assertTrue(output.hasMessage(app.WELCOME_MSG));
        assertTrue(output.hasMessage(app.MAIN_MENU_MSG));

        for (String s : app.MAIN_MENU_OPTIONS) {
            assertTrue(output.hasMessage(s));
        }
    }

    @Test
    public void testInvalidMenuOption() {
        input.add(Lists.newArrayList(Integer.toString(-1), app.EXIT_CODE));
        app.run();

        assertTrue(output.hasMessage(app.MENU_ERROR_MSG));
    }

    @Test
    public void testListBooks() {
        input.add(Lists.newArrayList(Integer.toString(app.LIST_BOOKS), app.EXIT_CODE));
        app.run();

        assertTrue(output.hasMessage(Book.HEADER));
        assertTrue(output.hasMessage(BibliotecaAppTester.BOOK_1.printString()));
        assertTrue(output.hasMessage(BibliotecaAppTester.BOOK_2.printString()));
        assertTrue(output.hasMessage(BibliotecaAppTester.BOOK_3.printString()));
        assertTrue(output.hasMessage(BibliotecaAppTester.BOOK_4.printString()));
    }

    @Test
    public void testListMovies() {
        input.add(Lists.newArrayList(Integer.toString(app.LIST_MOVIES), app.EXIT_CODE));
        app.run();

        assertTrue(output.hasMessage(Movie.HEADER));
        assertTrue(output.hasMessage(BibliotecaAppTester.MOVIE_1.printString()));
        assertTrue(output.hasMessage(BibliotecaAppTester.MOVIE_2.printString()));
        assertTrue(output.hasMessage(BibliotecaAppTester.MOVIE_3.printString()));
    }

    @Test
    public void testMenuLoop() {
        input.add(Lists.newArrayList(Integer.toString(app.LIST_BOOKS), Integer.toString(-1), app.EXIT_CODE));
        app.run();

        assertTrue(output.hasMessage(Book.HEADER));
        assertTrue(output.hasMessage(BibliotecaAppTester.BOOK_1.printString()));
        assertTrue(output.hasMessage(BibliotecaAppTester.BOOK_2.printString()));
        assertTrue(output.hasMessage(BibliotecaAppTester.BOOK_3.printString()));
        assertTrue(output.hasMessage(BibliotecaAppTester.BOOK_4.printString()));
        assertTrue(output.hasMessage(app.MENU_ERROR_MSG));
    }

    @Test
    public void testCheckoutBookSuccess() {
        input.add(Lists.newArrayList(Integer.toString(app.CHECKOUT_BOOK), BibliotecaAppTester.BOOK_3.title, app.EXIT_CODE));
        app.run();

        assertTrue(output.hasMessage(String.format(app.CHECKOUT_SUCCESS_MSG, app.BOOK.toLowerCase())));
    }

    @Test
    public void testCheckoutBookFail() {
        input.add(Lists.newArrayList(Integer.toString(app.CHECKOUT_BOOK), "-1", app.EXIT_CODE));
        app.run();

        assertTrue(output.hasMessage(String.format(app.CHECKOUT_FAIL_MSG, app.BOOK.toLowerCase())));
    }

    @Test
    public void testReturnBookSuccess() {
        input.add(Lists.newArrayList(Integer.toString(app.CHECKOUT_BOOK), BibliotecaAppTester.BOOK_3.title));
        input.add(Lists.newArrayList(Integer.toString(app.RETURN_BOOK), BibliotecaAppTester.BOOK_3.title, app.EXIT_CODE));
        app.run();

        assertTrue(output.hasMessage(String.format(app.RETURN_SUCCESS_MSG, app.BOOK.toLowerCase())));
    }

    @Test
    public void testReturnBookFail() {
        input.add(Lists.newArrayList(Integer.toString(app.RETURN_BOOK), BibliotecaAppTester.BOOK_3.title, app.EXIT_CODE));
        app.run();

        assertTrue(output.hasMessage(String.format(app.RETURN_FAIL_MSG, app.BOOK.toLowerCase())));
    }

}


