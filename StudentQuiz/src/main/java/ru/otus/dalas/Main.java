package ru.otus.dalas;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.dalas.domain.Question;
import ru.otus.dalas.service.QuestionService;
import ru.otus.dalas.service.TestService;
import ru.otus.dalas.service.TestServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        TestService testService = context.getBean(TestServiceImpl.class);
        testService.startTest();

    }

}
