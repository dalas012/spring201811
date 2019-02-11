package ru.otus.dalas.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    private final AuthorMapper authorMapper;
    private final GenreMapper genreMapper;

    public BookMapper(AuthorMapper authorMapper, GenreMapper genreMapper) {
        this.authorMapper = authorMapper;
        this.genreMapper = genreMapper;
    }

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Long bookId = resultSet.getLong("book_id");
        String bookTitle = resultSet.getString("book_title");
        Author author = authorMapper.mapRow(resultSet, i);
        Genre genre = genreMapper.mapRow(resultSet, i);
        return new Book(bookId, bookTitle, author, genre);
    }
}
