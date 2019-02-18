package ru.otus.dalas.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.otus.dalas.dao.interfaces.CommentDao;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Integer count() {
        TypedQuery<Long> query = em.createQuery("select count(c) from Comment as c", Long.class);
        return query.getSingleResult().intValue();
    }

    @Override
    @Transactional
    public void insert(Comment comment) {
        em.persist(comment);
    }

    @Override
    public Comment getById(Long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public List<Comment> getAll() {
        TypedQuery<Comment> query = em.createQuery(
                "select c from Comment c",
                Comment.class
        );
        return query.getResultList();
    }

    @Override
    public List<Comment> getByBook(Book book) {
        TypedQuery<Comment> query = em.createQuery(
                "select c from Comment c where c.book = :book",
                Comment.class
        );
        query.setParameter("book", book);
        return query.getResultList();
    }
}
