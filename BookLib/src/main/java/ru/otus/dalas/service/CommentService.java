package ru.otus.dalas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Comment;
import ru.otus.dalas.repositories.BookRepository;
import ru.otus.dalas.repositories.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    private BookRepository bookRepository;
    private CommentRepository repository;

    @Autowired
    public CommentService(BookRepository bookRepository, CommentRepository repository) {
        this.bookRepository = bookRepository;
        this.repository = repository;
    }

    public Long count() {
        return repository.count();
    }

    public void insert(Long bookId, String text) {
        Book book = bookRepository.findBookById(bookId);
        repository.save(new Comment(book, text));
    }

    public Comment getById(Long id) {
        return repository.findCommentById(id);
    }

    public List<Comment> getAll() {
        return repository.findAll();
    }

    public List<Comment> getByBook(Long bookId) {
        Book book = bookRepository.findBookById(bookId);
        return repository.findByBook(book);
    }


}
