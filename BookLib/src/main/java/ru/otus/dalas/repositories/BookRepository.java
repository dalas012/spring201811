package ru.otus.dalas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Genre;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Book save(Book book);
    long count();
    Book findBookById(Long id);
    List<Book> findAll();
    List<Book> findByAuthor(Author author);
    List<Book> findByGenre(Genre genre);

}
