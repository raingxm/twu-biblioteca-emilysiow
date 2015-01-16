package com.twu.biblioteca;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by esiow on 14/01/2015.
 */
public class BibliotecaAppTester {
    public static final Book BOOK_1 = new Book("Test-Driven Development By Example", "Kent Beck", 2003);
    public static final Book BOOK_2 = new Book("The Agile Samurai", "Jonathan Rasmusson", 2010);
    public static final Book BOOK_3 = new Book("Head First Java", "Kathy Sierra & Bert Bates", 2005);
    public static final Book BOOK_4 = new Book("Don't Make Me Think, Revisited", "Steve Krug", 2014);

    public static final Movie MOVIE_1 = new Movie("Into The Woods", "Rob Marshall", 2014, 7);
    public static final Movie MOVIE_2 = new Movie("Big Hero 6", "Don Hall & Chris Williams", 2014, 9);
    public static final Movie MOVIE_3 = new Movie("The Last Five Years", "Richard LaGravenese", 2015);

    public static final User USER_1 = new User(1237654, "password");
    public static final User USER_2 = new User(9871234, "pword");
    public static final User USER_3 = new User(8881234, "word");


    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp(new ConsoleInputHandler(), new ConsoleOutputHandler(), initBookList(), initMovieList(), initUserList());
        app.run();
    }

    static Collection<Book> initBookList() {
        Collection<Book> bookList = new ArrayList<Book>();
        bookList.add(BOOK_1);
        bookList.add(BOOK_2);
        bookList.add(BOOK_3);
        bookList.add(BOOK_4);
        return bookList;
    }

    static Collection<Movie> initMovieList() {
        Collection<Movie> movieList = new ArrayList<Movie>();
        movieList.add(MOVIE_1);
        movieList.add(MOVIE_2);
        movieList.add(MOVIE_3);
        return movieList;
    }

    static Collection<User> initUserList() {
        Collection<User> userList = new ArrayList<User>();
        userList.add(USER_1);
        userList.add(USER_2);
        userList.add(USER_3);
        return userList;
    }
}
