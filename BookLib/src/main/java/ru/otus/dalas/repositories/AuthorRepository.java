package ru.otus.dalas.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.dalas.model.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends MongoRepository<Author, ObjectId> {

    Author save(Author author);
    long count();
    Author findAuthorById(ObjectId id);
    List<Author> findAll();

}
