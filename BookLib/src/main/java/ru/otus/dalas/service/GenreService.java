package ru.otus.dalas.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    private BookRepository repository;

    @Autowired
    public GenreService(BookRepository repository) {
        this.repository = repository;
    }

    public Long count() {
        return (long) getAll().size();
    }

    public void insert(ObjectId bookId, String genre) {
        Book book = repository.findBookById(bookId);
        book.setGenre(genre);
        repository.save(book);
    }

    public List<String> getAll() {
        List<String> result = new ArrayList<>();
        for (Book book : repository.findAll()) {
            String genre = book.getGenre();
            if (genre != null && !genre.isEmpty() && !result.contains(genre)) {
                result.add(genre);
            }
        }
        return result;
    }

    public String getByBook(ObjectId bookId) {
        Book book = repository.findBookById(bookId);
        return book.getGenre();
    }

}
