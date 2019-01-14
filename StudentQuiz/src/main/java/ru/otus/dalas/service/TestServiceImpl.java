package ru.otus.dalas.service;

import ru.otus.dalas.domain.Question;

import java.util.List;

public class TestServiceImpl implements TestService {

    private QuestionService questionService;
    private IOService ioService;

    public TestServiceImpl(QuestionService questionService, IOService ioService) {
        this.questionService = questionService;
        this.ioService = ioService;
    }

    @Override
    public void startTest() {

        ioService.printLine("Starting test...");
        ioService.printLine("Enter first name: ");
        String firstName = ioService.readLine();
        ioService.printLine("Enter last name: ");
        String lastName = ioService.readLine();
        ioService.printLine("Answer the questions. Good luck!");

        int correctCount = 0;
        List<Question> questions = questionService.getTest();

        for (Question question : questions) {
            ioService.printLine(question.getQuestion());
            String answer = ioService.readLine();
            if (question.getAnswer().equalsIgnoreCase(answer)) {
                correctCount++;
            }
        }

        ioService.printLine("Great, " + firstName + " " + lastName + "! Your result is: "
                + correctCount + "/" + questions.size());

    }

}

