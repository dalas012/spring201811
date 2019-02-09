package ru.otus.dalas.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.dalas.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        Long genreId = resultSet.getLong("genre_id");
        String genreName = resultSet.getString("genre_name");
        return new Genre(genreId, genreName);
    }
}
