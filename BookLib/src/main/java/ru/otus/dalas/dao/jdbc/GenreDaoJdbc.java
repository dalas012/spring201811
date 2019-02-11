package ru.otus.dalas.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dalas.dao.interfaces.GenreDao;
import ru.otus.dalas.dao.mappers.GenreMapper;
import ru.otus.dalas.model.Genre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;
    private final GenreMapper mapper = new GenreMapper();


    @Autowired
    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public Integer count() {
        return jdbc.queryForObject("select count(*) from genres", new HashMap<>(), Integer.class);
    }

    @Override
    public void insert(Genre genre) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", genre.getId());
        params.put("name", genre.getName());
        jdbc.update("insert into genres values (:id, :name)", params);
    }

    @Override
    public Genre getById(Long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        try {
            return jdbc.queryForObject(
                    " select id as genre_id, name as genre_name " +
                            " from genres " +
                            " where id = :id ",
                    params, mapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query(
                " select id as genre_id, name as genre_name " +
                        " from genres ",
                mapper);
    }
}
