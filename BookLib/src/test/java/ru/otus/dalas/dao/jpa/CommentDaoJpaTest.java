package ru.otus.dalas.dao.jpa;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Comment;
import ru.otus.dalas.repositories.BookRepository;
import ru.otus.dalas.repositories.CommentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class CommentDaoJpaTest {

    @Autowired
    private CommentRepository repository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void count() {
        assertEquals(1, repository.count());
    }

    @Test
    void save() {
        Book book = bookRepository.findBookById(1L);
        repository.save(new Comment(book, "Very good"));
        assertEquals(2, repository.count());
        assertEquals("Very good", repository.findCommentById(2L).getText());
    }

    @Test
    void findCommentById() {
        Comment comment = repository.findCommentById(1L);
        assertEquals("Good book", comment.getText());
    }

    @Test
    void findAll() {
        List<Comment> comments = repository.findAll();
        assertEquals(1, comments.size());
    }

    @Test
    void findByBook() {
        Book book = bookRepository.findBookById(1L);
        List<Comment> comments = repository.findByBook(book);
        assertEquals(1, comments.size());
        assertEquals("Good book", comments.get(0).getText());
    }
}