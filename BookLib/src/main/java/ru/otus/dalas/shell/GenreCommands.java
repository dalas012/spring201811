package ru.otus.dalas.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dalas.dao.interfaces.GenreDao;
import ru.otus.dalas.model.Genre;

@ShellComponent
public class GenreCommands {

    private GenreDao dao;


    @Autowired
    public GenreCommands(GenreDao genreDao) {
        this.dao = genreDao;
    }


    @ShellMethod(key = "genreCount", value = "Genres count")
    public Integer genreCount() {
        return dao.count();
    }

    @ShellMethod(key = "genreInsert", value = "Insert genre")
    public String genreInsert(
            @ShellOption Long id,
            @ShellOption String name
    ) {
        dao.insert(new Genre(id, name));
        return "Done!";
    }

    @ShellMethod(key = "genreGet", value = "Get genre by ID")
    public String genreGet(
            @ShellOption Long id
    ) {
        return dao.getById(id).toString();
    }

    @ShellMethod(key = "genreAll", value = "All genres")
    public String genreAll() {
         return dao.getAll().toString();
    }
}
