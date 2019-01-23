package ru.otus.dalas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dalas.domain.Question;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    private MessageService messageService;
    private QuestionService questionService;
    private IOService ioService;

    @Autowired
    public TestServiceImpl(MessageService messageService,
                           QuestionService questionService,
                           IOService ioService) {
        this.messageService = messageService;
        this.questionService = questionService;
        this.ioService = ioService;
    }

    @Override
    public void startTest(String name) {

        ioService.printLine(messageService.getMessage("Starting-test", new String[] {name}));
        ioService.printLine(messageService.getMessage("Answer-the-questions-Good-luck"));

        int correctCount = 0;
        List<Question> questions = questionService.getTest();

        for (Question question : questions) {
            ioService.printLine(question.getQuestion());
            String answer = ioService.readLine();
            if (question.getAnswer().equalsIgnoreCase(answer)) {
                correctCount++;
            }
        }

        String[] params = new String[] {
                name, String.valueOf(correctCount), String.valueOf(questions.size())
        };
        ioService.printLine(messageService.getMessage("Result-message", params));

    }

}

