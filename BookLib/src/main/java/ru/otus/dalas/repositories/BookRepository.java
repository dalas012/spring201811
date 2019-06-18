package ru.otus.dalas.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, ObjectId> {

    Book save(Book book);
    long count();
    Book findBookById(ObjectId id);
    List<Book> findAll();
    List<Book> findByAuthor(Author author);
    List<Book> findByGenre(String genre);



}
