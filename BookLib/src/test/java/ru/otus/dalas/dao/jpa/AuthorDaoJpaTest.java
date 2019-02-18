package ru.otus.dalas.dao.jpa;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.dalas.dao.interfaces.AuthorDao;
import ru.otus.dalas.model.Author;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(AuthorDaoJpa.class)
class AuthorDaoJpaTest {

    @Autowired
    AuthorDao dao;

    @Test
    void count() {
        assertEquals(Integer.valueOf(3), dao.count());
    }

    @Test
    void insert() {
        dao.insert(new Author("Young writer"));
        assertEquals(Integer.valueOf(4), dao.count());
        assertEquals("Young writer", dao.getById(4L).getName());
    }

    @Test
    void getById() {
        Author author = dao.getById(1L);
        assertEquals("Isaac Asimov", author.getName());
    }

    @Test
    void getAll() {
        List<Author> authors = dao.getAll();
        assertEquals(3, authors.size());
    }
}