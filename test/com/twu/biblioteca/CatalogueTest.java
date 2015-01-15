package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by esiow on 15/01/2015.
 */
public class CatalogueTest {
    private Catalogue<Book> bookCatalogue;
    private Catalogue<Movie> movieCatalogue;

    @Before
    public void initialize() {
        bookCatalogue = new Catalogue<Book>(BibliotecaAppTester.initBookList());
        movieCatalogue = new Catalogue<Movie>(BibliotecaAppTester.initMovieList());
    }

    @Test
    public void testCheckoutBook() {
        assertTrue(bookCatalogue.isAvailable(BibliotecaAppTester.BOOK_1.title));
        bookCatalogue.checkoutItem(BibliotecaAppTester.BOOK_1.title);
        assertFalse(bookCatalogue.isAvailable(BibliotecaAppTester.BOOK_1.title));
    }

    @Test
    public void testReturnBook() {
        bookCatalogue.checkoutItem(BibliotecaAppTester.BOOK_1.title);
        assertFalse(bookCatalogue.isAvailable(BibliotecaAppTester.BOOK_1.title));
        bookCatalogue.returnItem(BibliotecaAppTester.BOOK_1.title);
        assertTrue(bookCatalogue.isAvailable(BibliotecaAppTester.BOOK_1.title));
    }


    @Test
    public void testCheckoutMovie() {
        assertTrue(movieCatalogue.isAvailable(BibliotecaAppTester.MOVIE_1.name));
        movieCatalogue.checkoutItem(BibliotecaAppTester.MOVIE_1.name);
        assertFalse(movieCatalogue.isAvailable(BibliotecaAppTester.MOVIE_1.name));
    }

}
