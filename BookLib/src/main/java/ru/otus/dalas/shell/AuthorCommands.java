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


    @ShellMethod(key = "authorCount", value = "Authors count")
    public Integer authorCount() {
        return dao.count();
    }

    @ShellMethod(key = "authorInsert", value = "Insert author")
    public String authorInsert(
            @ShellOption Long id,
            @ShellOption String name
    ) {
        dao.insert(new Author(id, name));
        return "Done!";
    }

    @ShellMethod(key = "authorGet", value = "Get author by ID")
    public String authorGet(
            @ShellOption Long id
    ) {
        return dao.getById(id).toString();
    }

    @ShellMethod(key = "authorAll", value = "All authors")
    public String authorAll() {
         return dao.getAll().toString();
    }
}
