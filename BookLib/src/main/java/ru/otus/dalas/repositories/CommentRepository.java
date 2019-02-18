package ru.otus.dalas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Comment save(Comment comment);
    long count();
    Comment findCommentById(Long id);
    List<Comment> findAll();
    List<Comment> findByBook(Book book);

}
