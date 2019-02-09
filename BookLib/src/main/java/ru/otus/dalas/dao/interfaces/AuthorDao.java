package ru.otus.dalas.dao.interfaces;

import ru.otus.dalas.model.Author;

import java.util.List;

public interface AuthorDao {
    Integer count();
    void insert(Author author);
    Author getById(Long id);
    List<Author> getAll();
}
