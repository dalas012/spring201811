package ru.otus.dalas.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.otus.dalas.dao.interfaces.AuthorDao;
import ru.otus.dalas.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Integer count() {
        TypedQuery<Long> query = em.createQuery("select count(a) from Author as a", Long.class);
        return query.getSingleResult().intValue();
    }

    @Override
    @Transactional
    public void insert(Author author) {
        em.persist(author);
    }

    @Override
    public Author getById(Long id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery(
                "select a from Author a",
                Author.class
        );
        return query.getResultList();
    }
}
