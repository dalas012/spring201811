package ru.otus.dalas.dao.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.dalas.dao.interfaces.AuthorDao;
import ru.otus.dalas.model.Author;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/before.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/after.sql")
class AuthorDaoJdbcTest {

    @Autowired
    AuthorDao dao;

    @Test
    void count() {
        assertEquals(Integer.valueOf(2), dao.count());
    }

    @Test
    void insert() {
        dao.insert(new Author(3L, "Young writer"));
        assertEquals(Integer.valueOf(3), dao.count());
        assertEquals("Young writer", dao.getById(3L).getName());
    }

    @Test
    void getById() {
        Author author = dao.getById(1L);
        assertEquals("Isaac Asimov", author.getName());
    }

    @Test
    void getAll() {
        List<Author> authors = dao.getAll();
        assertEquals(2, authors.size());
    }
}