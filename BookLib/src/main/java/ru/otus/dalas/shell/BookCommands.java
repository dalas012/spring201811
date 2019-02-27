package ru.otus.dalas.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.model.Genre;
import ru.otus.dalas.repositories.AuthorRepository;
import ru.otus.dalas.repositories.BookRepository;
import ru.otus.dalas.repositories.GenreRepository;

@ShellComponent
public class BookCommands {

    private BookRepository repository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;


    @Autowired
    public BookCommands(BookRepository repository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }


    @ShellMethod(key = "bookCount", value = "Books count")
    public Long bookCount() {
        return repository.count();
    }

    @ShellMethod(key = "bookInsert", value = "Insert book")
    public String bookInsert(
            @ShellOption String title,
            @ShellOption Long authorId,
            @ShellOption Long genreId
    ) {
        Author author = authorRepository.findAuthorById(authorId);
        Genre genre = genreRepository.findGenreById(genreId);
        repository.save(new Book(title, author, genre));
        return "Done!";
    }

    @ShellMethod(key = "bookGet", value = "Get book by ID")
    public String bookGet(
            @ShellOption Long id
    ) {
        return repository.findBookById(id).toString();
    }

    @ShellMethod(key = "bookAll", value = "All books")
    public String bookAll() {
        return repository.findAll().toString();
    }

    @ShellMethod(key = "bookAuthor", value = "Get books by Author")
    public String bookAuthor(
            @ShellOption Long authorId
    ) {
        Author author = authorRepository.findAuthorById(authorId);
        return repository.findByAuthor(author).toString();
    }

    @ShellMethod(key = "bookGenre", value = "Get books by Genre")
    public String bookGenre(
            @ShellOption Long genreId
    ) {
        Genre genre = genreRepository.findGenreById(genreId);
        return repository.findByGenre(genre).toString();
    }
}
