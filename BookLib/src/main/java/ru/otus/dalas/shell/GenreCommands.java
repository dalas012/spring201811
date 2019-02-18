package ru.otus.dalas.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dalas.model.Genre;
import ru.otus.dalas.repositories.GenreRepository;

@ShellComponent
public class GenreCommands {

    private GenreRepository repository;


    @Autowired
    public GenreCommands(GenreRepository repository) {
        this.repository = repository;
    }


    @ShellMethod(key = "genreCount", value = "Genres count")
    public Long genreCount() {
        return repository.count();
    }

    @ShellMethod(key = "genreInsert", value = "Insert genre")
    public String genreInsert(
            @ShellOption String name
    ) {
        repository.save(new Genre(name));
        return "Done!";
    }

    @ShellMethod(key = "genreGet", value = "Get genre by ID")
    public String genreGet(
            @ShellOption Long id
    ) {
        return repository.findGenreById(id).toString();
    }

    @ShellMethod(key = "genreAll", value = "All genres")
    public String genreAll() {
         return repository.findAll().toString();
    }
}
