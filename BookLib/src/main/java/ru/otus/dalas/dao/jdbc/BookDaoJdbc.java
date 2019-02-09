package ru.otus.dalas.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dalas.dao.interfaces.AuthorDao;
import ru.otus.dalas.dao.interfaces.BookDao;
import ru.otus.dalas.dao.interfaces.GenreDao;
import ru.otus.dalas.dao.mappers.AuthorMapper;
import ru.otus.dalas.dao.mappers.BookMapper;
import ru.otus.dalas.dao.mappers.GenreMapper;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Genre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final AuthorMapper authorMapper = new AuthorMapper();
    private final GenreMapper genreMapper = new GenreMapper();
    private final BookMapper mapper = new BookMapper(authorMapper, genreMapper);


    @Autowired
    public BookDaoJdbc(NamedParameterJdbcOperations jdbc, AuthorDao authorDao, GenreDao genreDao) {
        this.jdbc = jdbc;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }


    @Override
    public Integer count() {
        return jdbc.queryForObject("select count(*) from books", new HashMap<>(), Integer.class);
    }

    @Override
    public void insert(Book book) {
        authorDao.insert(book.getAuthor());
        genreDao.insert(book.getGenre());
        final Map<String, Object> params = new HashMap<>();
        params.put("id", book.getId());
        params.put("title", book.getTitle());
        params.put("author_id", book.getAuthor().getId());
        params.put("genre_id", book.getGenre().getId());
        jdbc.update("insert into books values (:id, :title, :author_id, :genre_id)", params);
    }

    @Override
    public Book getById(Long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.queryForObject(
                " select " +
                        "   b.id as book_id, " +
                        "   b.title as book_title, " +
                        "   a.id as author_id, " +
                        "   a.name as author_name, " +
                        "   g.id as genre_id, " +
                        "   g.name as genre_name " +
                        " from books as b " +
                        " left join authors as a on a.id = b.author_id " +
                        " left join genres as g on g.id = b.genre_id " +
                        " where b.id = :id ",
                params, mapper);
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                " select " +
                        "   b.id as book_id, " +
                        "   b.title as book_title, " +
                        "   a.id as author_id, " +
                        "   a.name as author_name, " +
                        "   g.id as genre_id, " +
                        "   g.name as genre_name " +
                        " from books as b " +
                        " left join authors as a on a.id = b.author_id " +
                        " left join genres as g on g.id = b.genre_id ",
                mapper);
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        final Map<String, Object> params = new HashMap<>();
        params.put("author_id", author.getId());
        return jdbc.query(
                " select " +
                        "   b.id as book_id, " +
                        "   b.title as book_title, " +
                        "   a.id as author_id, " +
                        "   a.name as author_name, " +
                        "   g.id as genre_id, " +
                        "   g.name as genre_name " +
                        " from books as b " +
                        " left join authors as a on a.id = b.author_id " +
                        " left join genres as g on g.id = b.genre_id " +
                        " where a.id = :author_id ",
                params, mapper);
    }

    @Override
    public List<Book> getByGenre(Genre genre) {
        final Map<String, Object> params = new HashMap<>();
        params.put("genre_id", genre.getId());
        return jdbc.query(
                " select " +
                        "   b.id as book_id, " +
                        "   b.title as book_title, " +
                        "   a.id as author_id, " +
                        "   a.name as author_name, " +
                        "   g.id as genre_id, " +
                        "   g.name as genre_name " +
                        " from books as b " +
                        " left join authors as a on a.id = b.author_id " +
                        " left join genres as g on g.id = b.genre_id " +
                        " where g.id = :genre_id ",
                params, mapper);
    }
}
