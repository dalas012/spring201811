package ru.otus.dalas.dao.jdbc;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.dalas.dao.interfaces.AuthorDao;
import ru.otus.dalas.dao.interfaces.BookDao;
import ru.otus.dalas.dao.interfaces.GenreDao;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
class BookDaoJdbcTest {

    @Autowired
    BookDao dao;
    @Autowired
    AuthorDao authorDao;
    @Autowired
    GenreDao genreDao;

    @Test
    void count() {
        assertEquals(Integer.valueOf(3), dao.count());
    }

    @Test
    void insert() {
        Author author = new Author(4L, "Young Writer");
        Genre genre = new Genre(3L, "New Genre");
        dao.insert(new Book(4L, "New Real Book", author, genre));
        assertEquals(Integer.valueOf(4), dao.count());
        assertEquals("New Real Book", dao.getById(4L).getTitle());
    }

    @Test
    void getById() {
        Book book = dao.getById(1L);
        assertEquals("Bicentennial man", book.getTitle());
    }

    @Test
    void getAll() {
        List<Book> books = dao.getAll();
        assertEquals(3, books.size());
    }

    @Test
    void getByAuthor() {
        Author author = authorDao.getById(1L);
        List<Book> books = dao.getByAuthor(author);
        assertEquals(1, books.size());
        assertEquals("Bicentennial man", books.get(0).getTitle());
    }

    @Test
    void getByGenre() {
        Genre genre = genreDao.getById(1L);
        List<Book> books = dao.getByGenre(genre);
        assertEquals(1, books.size());
        assertEquals("Bicentennial man", books.get(0).getTitle());
    }
}