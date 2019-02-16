package ru.otus.dalas.dao.interfaces;

import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Comment;

import java.util.List;

public interface CommentDao {
    Integer count();
    void insert(Comment comment);
    Comment getById(Long id);
    List<Comment> getAll();
    List<Comment> getByBook(Book book);
}
