package com.twu.biblioteca;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class BibliotecaAppTest {
    private static final int INVALID_OPTION = -1;
    private static final String INVALID_TITLE = "-1";
    private static final String INVALID_LIBRARY_NUM = "-1";
    private static final String INVALID_PASSWORD = "";
    private static final String USER_1_PASSWORD = "password";

    private TestInputHandler input = new TestInputHandler();
    private TestOutputHandler output = new TestOutputHandler();
    private Collection<Book> bookList  = BibliotecaAppTester.initBookList();
    private Collection<Movie> movieList = BibliotecaAppTester.initMovieList();
    private Collection<User> userList = BibliotecaAppTester.initUserList();

    private BibliotecaApp app;

    @Before
    public void initialize() {
        app = new BibliotecaApp(input, output, bookList, movieList, userList);
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
        input.add(Lists.newArrayList(Integer.toString(INVALID_OPTION), app.EXIT_CODE));
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
        input.add(Lists.newArrayList(Integer.toString(app.LIST_BOOKS), Integer.toString(INVALID_OPTION), app.EXIT_CODE));
        app.run();

        assertTrue(output.hasMessage(Book.HEADER));
        assertTrue(output.hasMessage(BibliotecaAppTester.BOOK_1.printString()));
        assertTrue(output.hasMessage(BibliotecaAppTester.BOOK_2.printString()));
        assertTrue(output.hasMessage(BibliotecaAppTester.BOOK_3.printString()));
        assertTrue(output.hasMessage(BibliotecaAppTester.BOOK_4.printString()));
        assertTrue(output.hasMessage(app.MENU_ERROR_MSG));
    }

    @Test
    public void testLoginSuccess() {
        input.add(Lists.newArrayList(BibliotecaAppTester.USER_1.libraryNum, USER_1_PASSWORD));
        boolean loginSuccess = app.userAuthenticate();

        assertTrue(loginSuccess);
    }

    @Test
    public void testLoginInvalidLibraryNumFail() {
        input.add(Lists.newArrayList(INVALID_LIBRARY_NUM));
        boolean loginSuccess = app.userAuthenticate();

        assertFalse(loginSuccess);
    }

    @Test
    public void testLoginInvalidPasswordFail() {
        input.add(Lists.newArrayList(BibliotecaAppTester.USER_1.libraryNum, INVALID_PASSWORD));
        boolean loginSuccess = app.userAuthenticate();

        assertFalse(loginSuccess);
    }

    @Test
    public void testCheckoutBookSuccess() {
        input.add(Lists.newArrayList(Integer.toString(app.CHECKOUT_BOOK),BibliotecaAppTester.USER_1.libraryNum, USER_1_PASSWORD, BibliotecaAppTester.BOOK_3.title, app.EXIT_CODE));
        app.run();

        assertTrue(output.hasMessage(String.format(app.CHECKOUT_SUCCESS_MSG, app.BOOK.toLowerCase())));
    }

    @Test
    public void testCheckoutBookFail() {
        input.add(Lists.newArrayList(Integer.toString(app.CHECKOUT_BOOK), BibliotecaAppTester.USER_1.libraryNum, USER_1_PASSWORD, INVALID_TITLE, app.EXIT_CODE));
        app.run();

        assertTrue(output.hasMessage(String.format(app.CHECKOUT_FAIL_MSG, app.BOOK.toLowerCase())));
    }

    @Test
    public void testReturnBookSuccess() {
        input.add(Lists.newArrayList(Integer.toString(app.CHECKOUT_BOOK), BibliotecaAppTester.USER_1.libraryNum, USER_1_PASSWORD, BibliotecaAppTester.BOOK_3.title));
        input.add(Lists.newArrayList(Integer.toString(app.RETURN_BOOK), BibliotecaAppTester.USER_1.libraryNum, USER_1_PASSWORD, BibliotecaAppTester.BOOK_3.title, app.EXIT_CODE));
        app.run();

        assertTrue(output.hasMessage(String.format(app.RETURN_SUCCESS_MSG, app.BOOK.toLowerCase())));
    }

    @Test
    public void testReturnBookFail() {
        input.add(Lists.newArrayList(Integer.toString(app.RETURN_BOOK), BibliotecaAppTester.USER_1.libraryNum, USER_1_PASSWORD, BibliotecaAppTester.BOOK_3.title, app.EXIT_CODE));
        app.run();

        assertTrue(output.hasMessage(String.format(app.RETURN_FAIL_MSG, app.BOOK.toLowerCase())));
    }

    @Test
    public void testCheckoutMovieSuccess() {
        input.add(Lists.newArrayList(Integer.toString(app.CHECKOUT_MOVIE), BibliotecaAppTester.USER_1.libraryNum, USER_1_PASSWORD, BibliotecaAppTester.MOVIE_1.name, app.EXIT_CODE));
        app.run();

        assertTrue(output.hasMessage(String.format(app.CHECKOUT_SUCCESS_MSG, app.MOVIE.toLowerCase())));
    }

    @Test
    public void testCheckoutMovieFail() {
        input.add(Lists.newArrayList(Integer.toString(app.CHECKOUT_MOVIE), BibliotecaAppTester.USER_1.libraryNum, USER_1_PASSWORD, INVALID_TITLE, app.EXIT_CODE));
        app.run();

        assertTrue(output.hasMessage(String.format(app.CHECKOUT_FAIL_MSG, app.MOVIE.toLowerCase())));
    }

    @Test
    public void testUserNotLoggedIn() {
        User u = app.getCurrentUser();
        assertNull(u);
    }

    @Test
    public void testUserLoggedIn() {
        input.add(Lists.newArrayList(BibliotecaAppTester.USER_1.libraryNum, USER_1_PASSWORD));
        app.userAuthenticate();

        User u = app.getCurrentUser();
        assertNotNull(u);
    }

}


