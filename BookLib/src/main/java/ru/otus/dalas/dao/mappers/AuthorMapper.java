package ru.otus.dalas.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.dalas.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        Long authorId = resultSet.getLong("author_id");
        String authorName = resultSet.getString("author_name");
        return new Author(authorId, authorName);
    }
}
