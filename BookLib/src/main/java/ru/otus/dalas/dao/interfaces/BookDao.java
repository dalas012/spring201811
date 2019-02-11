package ru.otus.dalas.dao.interfaces;

import ru.otus.dalas.model.Author;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Genre;

import java.util.List;

public interface BookDao {
    Integer count();
    void insert(Book book);
    Book getById(Long id);
    List<Book> getAll();
    List<Book> getByAuthor(Author author);
    List<Book> getByGenre(Genre genre);
}
