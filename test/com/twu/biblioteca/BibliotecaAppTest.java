package com.twu.biblioteca;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BibliotecaAppTest {
    private TestInputHandler input = new TestInputHandler();
    private TestOutputHandler output = new TestOutputHandler();
    private BibliotecaApp app;

    @Before
    public void initialize() {
        app = new BibliotecaApp(input, output, BibliotecaAppTester.initBookList());
    }

    @Test
    public void testBibliotecaStartup() {
        input.add(app.EXIT_CODE);
        app.run();

        assertTrue(output.hasMessage(app.WELCOME_MESSAGE));
        assertTrue(output.hasMessage(app.MAIN_MENU_MESSAGE));

        for (String s : app.MAIN_MENU_OPTIONS) {
            assertTrue(output.hasMessage(s));
        }
    }
}


