package ru.otus.dalas.dao.jpa;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.dalas.dao.interfaces.GenreDao;
import ru.otus.dalas.model.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(GenreDaoJpa.class)
class GenreDaoJpaTest {

    @Autowired
    GenreDao dao;

    @Test
    void count() {
        assertEquals(Integer.valueOf(2), dao.count());
    }

    @Test
    void insert() {
        dao.insert(new Genre("New genre"));
        assertEquals(Integer.valueOf(3), dao.count());
        assertEquals("New genre", dao.getById(3L).getName());
    }

    @Test
    void getById() {
        Genre genre = dao.getById(1L);
        assertEquals("Fantasy", genre.getName());
    }

    @Test
    void getAll() {
        List<Genre> genres = dao.getAll();
        assertEquals(2, genres.size());
    }
}