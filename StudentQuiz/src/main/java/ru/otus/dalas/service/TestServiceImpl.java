package ru.otus.dalas.service;

import ru.otus.dalas.domain.Question;

import java.util.List;
import java.util.Scanner;

public class TestServiceImpl implements TestService {

    private QuestionService questionService;

    public TestServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void startTest() {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Starting test...");
            System.out.println("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name: ");
            String lastName = scanner.nextLine();
            System.out.println("Answer the questions. Good luck!");

            int correctCount = 0;
            List<Question> questions = questionService.getTest();

            for (Question question : questions) {
                System.out.println(question.getQuestion());
                String answer = scanner.nextLine();
                if (question.getAnswer().equalsIgnoreCase(answer)) {
                    correctCount++;
                }
            }

            System.out.println("Great, " + firstName + " " + lastName + "! Your result is: "
                    + correctCount + "/" + questions.size());

        }

    }

}

