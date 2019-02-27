package ru.otus.dalas.service;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.repositories.AuthorRepository;
import ru.otus.dalas.repositories.BookRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@Import(GenreService.class)
class GenreServiceTest {

    @Autowired
    GenreService service;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    MongoTemplate mongoTemplate;


    static final String AUTHOR_HEX_STRING = "666f6f2d6261722d71757578";
    static final String BOOK_HEX_STRING = "5c75b770ea0a287835fa5399";


    @AfterEach
    void clean() {
        mongoTemplate.dropCollection(Author.class);
        mongoTemplate.dropCollection(Book.class);
    }

    @BeforeEach
    void setup() throws Exception {
        Author author1 = new Author("TestAuthor1");
        author1.setId(new ObjectId(AUTHOR_HEX_STRING));
        mongoTemplate.save(author1);
        Author author2 = mongoTemplate.save(new Author("TestAuthor2"));
        Author author3 = mongoTemplate.save(new Author("TestAuthor3"));
        Book book1 = new Book("TestBook1", author1, "TestGenre1");
        book1.setId(new ObjectId(BOOK_HEX_STRING));
        mongoTemplate.save(book1);
        mongoTemplate.save(new Book("TestBook2", author2, "TestGenre2"));
        mongoTemplate.save(new Book("TestBook3", author3, "TestGenre3"));
    }

    @Test
    void count() {
        assertEquals(3, (long) service.count());
    }

    @Test
    void insert() {
        service.insert(new ObjectId(BOOK_HEX_STRING), "BLABLAGENRE");
        Book book = bookRepository.findBookById(new ObjectId(BOOK_HEX_STRING));
        assertEquals("BLABLAGENRE", book.getGenre());
    }

    @Test
    void getAll() {
        List<String> all = service.getAll();
        assertEquals(3, all.size());
    }

    @Test
    void getByBook() {
        String genre = service.getByBook(new ObjectId(BOOK_HEX_STRING));
        assertEquals("TestGenre1", genre);
    }
}