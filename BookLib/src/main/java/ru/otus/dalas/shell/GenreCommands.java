package ru.otus.dalas.shell;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dalas.service.GenreService;

@ShellComponent
public class GenreCommands {

    private GenreService service;


    @Autowired
    public GenreCommands(GenreService service) {
        this.service = service;
    }


    @ShellMethod(key = "genreCount", value = "Genres count")
    public Long genreCount() {
        return service.count();
    }

    @ShellMethod(key = "genreInsert", value = "Insert genre into book")
    public String genreInsert(
            @ShellOption ObjectId bookId,
            @ShellOption String name
    ) {
        service.insert(bookId, name);
        return "Done!";
    }

    @ShellMethod(key = "genreAll", value = "All genres")
    public String genreAll() {
         return service.getAll().toString();
    }
}
