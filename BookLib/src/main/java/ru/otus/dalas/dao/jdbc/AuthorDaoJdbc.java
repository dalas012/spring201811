package ru.otus.dalas.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dalas.dao.interfaces.AuthorDao;
import ru.otus.dalas.dao.mappers.AuthorMapper;
import ru.otus.dalas.model.Author;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;
    private final AuthorMapper mapper = new AuthorMapper();


    @Autowired
    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public Integer count() {
        return jdbc.queryForObject("select count(*) from authors", new HashMap<>(), Integer.class);
    }

    @Override
    public void insert(Author author) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", author.getId());
        params.put("name", author.getName());
        jdbc.update("insert into authors values (:id, :name)", params);
    }

    @Override
    public Author getById(Long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.queryForObject(
                " select id as author_id, name as author_name " +
                        " from authors " +
                        " where id = :id ",
                params, mapper);
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query(
                " select id as author_id, name as author_name " +
                        " from authors ",
                mapper);
    }
}
