package ru.otus.dalas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.dalas.model.Genre;

import java.util.List;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {

    Genre save(Genre genre);
    long count();
    Genre findGenreById(Long id);
    List<Genre> findAll();
}
