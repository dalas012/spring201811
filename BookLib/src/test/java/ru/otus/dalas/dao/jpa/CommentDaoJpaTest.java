package ru.otus.dalas.dao.jpa;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.dalas.dao.interfaces.BookDao;
import ru.otus.dalas.dao.interfaces.CommentDao;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Comment;
import ru.otus.dalas.model.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({CommentDaoJpa.class, BookDaoJpa.class})
class CommentDaoJpaTest {

    @Autowired
    private CommentDao dao;
    @Autowired
    private BookDao bookDao;

    @Test
    void count() {
        assertEquals(Integer.valueOf(1), dao.count());
    }

    @Test
    void insert() {
        Book book = bookDao.getById(1L);
        dao.insert(new Comment(book, "Very good"));
        assertEquals(Integer.valueOf(2), dao.count());
        assertEquals("Very good", dao.getById(2L).getText());
    }

    @Test
    void getById() {
        Comment comment = dao.getById(1L);
        assertEquals("Good book", comment.getText());
    }

    @Test
    void getAll() {
        List<Comment> comments = dao.getAll();
        assertEquals(1, comments.size());
    }

    @Test
    void getByBook() {
        Book book = bookDao.getById(1L);
        List<Comment> comments = dao.getByBook(book);
        assertEquals(1, comments.size());
        assertEquals("Good book", comments.get(0).getText());
    }
}