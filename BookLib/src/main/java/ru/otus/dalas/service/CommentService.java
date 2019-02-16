package ru.otus.dalas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dalas.dao.interfaces.BookDao;
import ru.otus.dalas.dao.interfaces.CommentDao;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Comment;

import java.util.List;

@Service
public class CommentService {

    private BookDao bookDao;
    private CommentDao dao;

    @Autowired
    public CommentService(BookDao bookDao, CommentDao dao) {
        this.bookDao = bookDao;
        this.dao = dao;
    }

    public Integer count() {
        return dao.count();
    }

    public void insert(Long bookId, String text) {
        Book book = bookDao.getById(bookId);
        dao.insert(new Comment(book, text));
    }

    public Comment getById(Long id) {
        return dao.getById(id);
    }

    public List<Comment> getAll() {
        return dao.getAll();
    }

    public List<Comment> getByBook(Long bookId) {
        Book book = bookDao.getById(bookId);
        return dao.getByBook(book);
    }


}
