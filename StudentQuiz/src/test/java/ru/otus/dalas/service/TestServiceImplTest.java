package ru.otus.dalas.service;

import org.junit.Test;
import ru.otus.dalas.dao.QuestionDao;
import ru.otus.dalas.dao.QuestionDaoImpl;
import ru.otus.dalas.domain.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestServiceImplTest {

    @Test
    public void startTest() {

        List<Question> questions = new ArrayList<>(Arrays.asList(new Question[]{
                new Question("a", "a"),
                new Question("b", "b"),
                new Question("c", "c"),
        }));

        QuestionDao dao = mock(QuestionDaoImpl.class);
        when(dao.getQuestions()).thenReturn(questions);

        QuestionService questionService = new QuestionServiceImpl();
        ((QuestionServiceImpl) questionService).setDao(dao);

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream stdout = new PrintStream(outStream);
        System.setOut(stdout);
        System.setIn(new ByteArrayInputStream("My\nName\na\nb\nd\n".getBytes()));

        TestService testService = new TestServiceImpl(questionService);
        testService.startTest();

        assertTrue(outStream.toString().contains("2/3"));

    }

}