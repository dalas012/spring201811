package ru.otus.dalas.shell;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dalas.service.CommentService;

@ShellComponent
public class CommentCommands {

    private CommentService service;

    @Autowired
    public CommentCommands(CommentService service) {
        this.service = service;
    }

    @ShellMethod(key = "commentCount", value = "Comments count")
    public Long commentCount() {
        return service.count();
    }

    @ShellMethod(key = "commentInsert", value = "Insert comment into book")
    public String commentInsert(
            @ShellOption ObjectId bookId,
            @ShellOption String text
    ) {
        service.insert(bookId, text);
        return "Done!";
    }

    @ShellMethod(key = "commentAll", value = "All comments")
    public String commentAll() {
        return service.getAll().toString();
    }

    @ShellMethod(key = "commentBook", value = "Get comments by book")
    public String commentBook(
            @ShellOption ObjectId bookId
    ) {
        return service.getByBook(bookId).toString();
    }

}
