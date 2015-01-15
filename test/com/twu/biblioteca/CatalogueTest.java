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

    @Before
    public void initialize() {
        bookCatalogue = new Catalogue<Book>(BibliotecaAppTester.initBookList());
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

}
