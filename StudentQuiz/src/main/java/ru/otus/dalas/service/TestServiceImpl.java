package ru.otus.dalas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.dalas.domain.Question;

import java.util.List;
import java.util.Locale;

@Service
@PropertySource("classpath:config.properties")
public class TestServiceImpl implements TestService {

    private MessageSource messageSource;
    private QuestionService questionService;
    private IOService ioService;
    private Locale locale;

    @Autowired
    public TestServiceImpl(MessageSource messageSource,
                           QuestionService questionService,
                           IOService ioService,
                           Locale locale) {
        this.messageSource = messageSource;
        this.questionService = questionService;
        this.ioService = ioService;
        this.locale = locale;
    }

    @Override
    public void startTest() {

        String[] params = new String[0];

        ioService.printLine(messageSource.getMessage("Starting-test", params, locale));
        ioService.printLine(messageSource.getMessage("Enter-first-name", params, locale));
        String firstName = ioService.readLine();
        ioService.printLine(messageSource.getMessage("Enter-last-name", params, locale));
        String lastName = ioService.readLine();
        ioService.printLine(messageSource.getMessage("Answer-the-questions-Good-luck", params, locale));

        int correctCount = 0;
        List<Question> questions = questionService.getTest();

        for (Question question : questions) {
            ioService.printLine(question.getQuestion());
            String answer = ioService.readLine();
            if (question.getAnswer().equalsIgnoreCase(answer)) {
                correctCount++;
            }
        }

        params = new String[] {firstName, lastName, String.valueOf(correctCount), String.valueOf(questions.size())};
        ioService.printLine(messageSource.getMessage("Result-message", params, locale));

    }

}

