package ru.otus.dalas.dao.interfaces;

import ru.otus.dalas.model.Genre;

import java.util.List;

public interface GenreDao {
    Integer count();
    void insert(Genre genre);
    Genre getById(Long id);
    List<Genre> getAll();
}
