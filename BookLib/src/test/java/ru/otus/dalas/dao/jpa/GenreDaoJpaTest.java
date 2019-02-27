package ru.otus.dalas.dao.jpa;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.dalas.model.Genre;
import ru.otus.dalas.repositories.GenreRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class GenreDaoJpaTest {

    @Autowired
    GenreRepository repository;

    @Test
    void count() {
        assertEquals(2, repository.count());
    }

    @Test
    void save() {
        repository.save(new Genre("New genre"));
        assertEquals(3, repository.count());
        assertEquals("New genre", repository.findGenreById(3L).getName());
    }

    @Test
    void findGenreById() {
        Genre genre = repository.findGenreById(1L);
        assertEquals("Fantasy", genre.getName());
    }

    @Test
    void findAll() {
        List<Genre> genres = repository.findAll();
        assertEquals(2, genres.size());
    }
}