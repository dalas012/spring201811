package ru.otus.dalas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.dalas.model.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    Author save(Author author);
    long count();
    Author findAuthorById(Long id);
    List<Author> findAll();

}
