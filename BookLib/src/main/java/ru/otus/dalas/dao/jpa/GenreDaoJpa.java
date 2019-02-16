package ru.otus.dalas.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.otus.dalas.dao.interfaces.GenreDao;
import ru.otus.dalas.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class GenreDaoJpa implements GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Integer count() {
        TypedQuery<Long> query = em.createQuery("select count(g) from Genre as g", Long.class);
        return query.getSingleResult().intValue();
    }

    @Override
    @Transactional
    public void insert(Genre genre) {
        em.persist(genre);
    }

    @Override
    public Genre getById(Long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery(
                "select g from Genre g",
                Genre.class
        );
        return query.getResultList();
    }
}
