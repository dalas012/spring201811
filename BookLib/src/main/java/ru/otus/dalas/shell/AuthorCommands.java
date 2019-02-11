package ru.otus.dalas.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dalas.dao.interfaces.AuthorDao;
import ru.otus.dalas.model.Author;

@ShellComponent
public class AuthorCommands {

    private AuthorDao dao;


    @Autowired
    public AuthorCommands(AuthorDao authorDao) {
        this.dao = authorDao;
    }


    @ShellMethod("Authors count")
    public Integer authorCount() {
        return dao.count();
    }

    @ShellMethod("Insert author")
    public String authorInsert(
            @ShellOption Long id,
            @ShellOption String name
    ) {
        dao.insert(new Author(id, name));
        return "Done!";
    }

    @ShellMethod("Get author by ID")
    public String authorGet(
            @ShellOption Long id
    ) {
        return dao.getById(id).toString();
    }

    @ShellMethod("All authors")
    public String authorAll() {
         return dao.getAll().toString();
    }
}
