package ru.otus.dalas.dao.jpa;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.repositories.AuthorRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository repository;

    @Test
    void count() {
        assertEquals(3, repository.count());
    }

    @Test
    void save() {
        repository.save(new Author("Young writer"));
        assertEquals(4, repository.count());
        assertEquals("Young writer", repository.findAuthorById(4L).getName());
    }

    @Test
    void findAuthorById() {
        Author author = repository.findAuthorById(1L);
        assertEquals("Isaac Asimov", author.getName());
    }

    @Test
    void findAll() {
        List<Author> authors = repository.findAll();
        assertEquals(3, authors.size());
    }
}