package ru.otus.dalas.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.repositories.AuthorRepository;

@ShellComponent
public class AuthorCommands {

    private AuthorRepository repository;


    @Autowired
    public AuthorCommands(AuthorRepository repository) {
        this.repository = repository;
    }


    @ShellMethod(key = "authorCount", value = "Authors count")
    public Long authorCount() {
        return repository.count();
    }

    @ShellMethod(key = "authorInsert", value = "Insert author")
    public String authorInsert(
            @ShellOption String name
    ) {
        repository.save(new Author(name));
        return "Done!";
    }

    @ShellMethod(key = "authorGet", value = "Get author by ID")
    public String authorGet(
            @ShellOption Long id
    ) {
        return repository.findAuthorById(id).toString();
    }

    @ShellMethod(key = "authorAll", value = "All authors")
    public String authorAll() {
         return repository.findAll().toString();
    }
}
