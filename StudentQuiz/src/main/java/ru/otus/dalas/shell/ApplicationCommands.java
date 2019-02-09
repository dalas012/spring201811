package ru.otus.dalas.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dalas.service.TestService;

@ShellComponent
public class ApplicationCommands {

    private TestService testService;

    @Autowired
    public ApplicationCommands(TestService testService) {
        this.testService = testService;
    }

    @ShellMethod("Start test")
    public void start(@ShellOption String name) {
        testService.startTest(name);
    }
}
