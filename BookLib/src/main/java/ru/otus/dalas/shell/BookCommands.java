package ru.otus.dalas.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dalas.dao.interfaces.AuthorDao;
import ru.otus.dalas.dao.interfaces.BookDao;
import ru.otus.dalas.dao.interfaces.GenreDao;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Genre;

@ShellComponent
public class BookCommands {

    private BookDao dao;
    private AuthorDao authorDao;
    private GenreDao genreDao;


    @Autowired
    public BookCommands(BookDao dao, AuthorDao authorDao, GenreDao genreDao) {
        this.dao = dao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }


    @ShellMethod(key = "bookCount", value = "Books count")
    public Integer bookCount() {
        return dao.count();
    }

    @ShellMethod(key = "bookInsert", value = "Insert book")
    public String bookInsert(
            @ShellOption Long id,
            @ShellOption String title,
            @ShellOption Long authorId,
            @ShellOption Long genreId
    ) {
        Author author = authorDao.getById(authorId);
        Genre genre = genreDao.getById(genreId);
        dao.insert(new Book(id, title, author, genre));
        return "Done!";
    }

    @ShellMethod(key = "bookGet", value = "Get book by ID")
    public String bookGet(
            @ShellOption Long id
    ) {
        return dao.getById(id).toString();
    }

    @ShellMethod(key = "bookAll", value = "All books")
    public String bookAll() {
        return dao.getAll().toString();
    }

    @ShellMethod(key = "bookAuthor", value = "Get books by Author")
    public String bookAuthor(
            @ShellOption Long authorId
    ) {
        Author author = authorDao.getById(authorId);
        return dao.getByAuthor(author).toString();
    }

    @ShellMethod(key = "bookGenre", value = "Get books by Genre")
    public String bookGenre(
            @ShellOption Long genreId
    ) {
        Genre genre = genreDao.getById(genreId);
        return dao.getByGenre(genre).toString();
    }
}
