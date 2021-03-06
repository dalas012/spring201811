package ru.otus.dalas.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private BookRepository repository;

    @Autowired
    public CommentService(BookRepository repository) {
        this.repository = repository;
    }

    public Long count() {
        return (long) getAll().size();
    }

    public void insert(ObjectId bookId, String text) {
        Book book = repository.findBookById(bookId);
        if (book.getComments() == null) {
            book.setComments(new ArrayList<>());
        }
        book.getComments().add(text);
        repository.save(book);
    }

    public List<String> getAll() {
        List<String> result = new ArrayList<>();
        for (Book book : repository.findAll()) {
            if (book.getComments() != null && !book.getComments().isEmpty()) {
                result.addAll(book.getComments());
            }
        }
        return result;
    }

    public List<String> getByBook(ObjectId bookId) {
        Book book = repository.findBookById(bookId);
        return book.getComments();
    }

}
