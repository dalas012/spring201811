package ru.otus.dalas.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.otus.dalas.dao.interfaces.BookDao;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Integer count() {
        TypedQuery<Long> query = em.createQuery("select count(b) from Book as b", Long.class);
        return query.getSingleResult().intValue();
    }

    @Override
    @Transactional
    public void insert(Book book) {
        em.persist(book);
    }

    @Override
    public Book getById(Long id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b",
                Book.class
        );
        return query.getResultList();
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where b.author = :author",
                Book.class
        );
        query.setParameter("author", author);
        return query.getResultList();
    }

    @Override
    public List<Book> getByGenre(Genre genre) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where b.genre = :genre",
                Book.class
        );
        query.setParameter("genre", genre);
        return query.getResultList();
    }
}
