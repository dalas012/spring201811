package ru.otus.dalas;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.dalas.service.TestService;
import ru.otus.dalas.service.TestServiceImpl;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                CommonConfig.class);

        TestService testService = context.getBean(TestServiceImpl.class);
        testService.startTest();

    }

}
