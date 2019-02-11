package ru.otus.dalas;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.dalas.dao.interfaces.BookDao;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Genre;

import java.sql.SQLException;

@SpringBootApplication
public class Main {

    private static final Author ASIMOV = new Author(1L, "Isaac Asimov");
    private static final Author LONDON = new Author(2L, "Jack London");

    private static final Genre FANTASY = new Genre(1L, "Fantasy");
    private static final Genre ADVENTURE = new Genre(2L, "Adventure");

    private static final Book BICENTENNIAL_MAN = new Book(1L, "Bicentennial man", ASIMOV, FANTASY);
    private static final Book WHITE_FANG = new Book(2L, "White Fang", LONDON, ADVENTURE);


    public static void main(String[] args) throws SQLException {

        ApplicationContext context = SpringApplication.run(Main.class);

        BookDao bookDao = context.getBean(BookDao.class);

        bookDao.insert(BICENTENNIAL_MAN);
        bookDao.insert(WHITE_FANG);

        Console.main(args);

    }

}
