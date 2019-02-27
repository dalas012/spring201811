package ru.otus.dalas.dao.jpa;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Genre;
import ru.otus.dalas.repositories.AuthorRepository;
import ru.otus.dalas.repositories.BookRepository;
import ru.otus.dalas.repositories.GenreRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class BookDaoJpaTest {

    @Autowired
    BookRepository repository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;

    @Test
    void count() {
        assertEquals(3, repository.count());
    }

    @Test
    void save() {
        Author author = authorRepository.findAuthorById(1L);
        Genre genre = genreRepository.findGenreById(1L);
        repository.save(new Book("New Real Book", author, genre));
        assertEquals(4, repository.count());
        assertEquals("New Real Book", repository.findBookById(4L).getTitle());
    }

    @Test
    void findBookById() {
        Book book = repository.findBookById(1L);
        assertEquals("Bicentennial man", book.getTitle());
    }

    @Test
    void findAll() {
        List<Book> books = repository.findAll();
        assertEquals(3, books.size());
    }

    @Test
    void findByAuthor() {
        Author author = authorRepository.findAuthorById(1L);
        List<Book> books = repository.findByAuthor(author);
        assertEquals(1, books.size());
        assertEquals("Bicentennial man", books.get(0).getTitle());
    }

    @Test
    void findByGenre() {
        Genre genre = genreRepository.findGenreById(1L);
        List<Book> books = repository.findByGenre(genre);
        assertEquals(1, books.size());
        assertEquals("Bicentennial man", books.get(0).getTitle());
    }
}