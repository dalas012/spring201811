package ru.otus.dalas.shell;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.model.Book;
import ru.otus.dalas.repositories.AuthorRepository;
import ru.otus.dalas.repositories.BookRepository;

@ShellComponent
public class BookCommands {

    private BookRepository repository;
    private AuthorRepository authorRepository;


    @Autowired
    public BookCommands(BookRepository repository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
    }


    @ShellMethod(key = "bookCount", value = "Books count")
    public Long bookCount() {
        return repository.count();
    }

    @ShellMethod(key = "bookInsert", value = "Insert book")
    public String bookInsert(
            @ShellOption String title,
            @ShellOption ObjectId authorId,
            @ShellOption String genre
    ) {
        Author author = authorRepository.findAuthorById(authorId);
        repository.save(new Book(title, author, genre));
        return "Done!";
    }

    @ShellMethod(key = "bookGet", value = "Get book by ID")
    public String bookGet(
            @ShellOption ObjectId id
    ) {
        return repository.findBookById(id).toString();
    }

    @ShellMethod(key = "bookAll", value = "All books")
    public String bookAll() {
        return repository.findAll().toString();
    }

    @ShellMethod(key = "bookAuthor", value = "Get books by Author")
    public String bookAuthor(
            @ShellOption ObjectId authorId
    ) {
        Author author = authorRepository.findAuthorById(authorId);
        return repository.findByAuthor(author).toString();
    }

    @ShellMethod(key = "bookGenre", value = "Get books by Genre")
    public String bookGenre(
            @ShellOption String genre
    ) {
        return repository.findByGenre(genre).toString();
    }
}
