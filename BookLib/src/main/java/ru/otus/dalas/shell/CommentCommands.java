package ru.otus.dalas.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dalas.model.Comment;
import ru.otus.dalas.service.CommentService;

@ShellComponent
public class CommentCommands {

    private CommentService service;

    @Autowired
    public CommentCommands(CommentService service) {
        this.service = service;
    }

    @ShellMethod(key = "commentCount", value = "Comments count")
    public Integer commentCount() {
        return service.count();
    }

    @ShellMethod(key = "commentInsert", value = "Insert comment")
    public String commentInsert(
            @ShellOption Long bookId,
            @ShellOption String text
    ) {
        service.insert(bookId, text);
        return "Done!";
    }

    @ShellMethod(key = "commentGet", value = "Get comment by ID")
    public String commentGet(
            @ShellOption Long id
    ) {
        Comment comment = service.getById(id);
        if (comment != null) {
            return comment.toString();
        } else {
            return "Not found!";
        }
    }

    @ShellMethod(key = "commentAll", value = "All comments")
    public String commentAll() {
        return service.getAll().toString();
    }

    @ShellMethod(key = "commentBook", value = "Get comments by book")
    public String commentBook(
            @ShellOption Long bookId
    ) {
        return service.getByBook(bookId).toString();
    }

}
