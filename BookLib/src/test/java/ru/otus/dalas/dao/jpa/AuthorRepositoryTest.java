package ru.otus.dalas.dao.jpa;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.repositories.AuthorRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository repository;
    @Autowired
    MongoTemplate mongoTemplate;

    static final String HEX_STRING = "666f6f2d6261722d71757578";


    @AfterEach
    void clean() {
        mongoTemplate.dropCollection(Author.class);
    }

    @BeforeEach
    void setup() throws Exception {
        Author author1 = new Author("TestAuthor1");
        author1.setId(new ObjectId(HEX_STRING));
        mongoTemplate.save(author1);
        mongoTemplate.save(new Author("TestAuthor2"));
        mongoTemplate.save(new Author("TestAuthor3"));
    }


    @Test
    void count() {
        assertEquals(3, repository.count());
    }

    @Test
    void save() {
        Author author = repository.save(new Author("Young writer"));
        assertEquals(4, repository.count());
        assertEquals("Young writer", repository.findAuthorById(author.getId()).getName());
    }

    @Test
    void findAuthorById() {
        Author author = repository.findAuthorById(new ObjectId(HEX_STRING));
        assertEquals("TestAuthor1", author.getName());
    }

    @Test
    void findAll() {
        List<Author> authors = repository.findAll();
        assertEquals(3, authors.size());
    }
}