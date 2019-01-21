package ru.otus.dalas.service;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.MessageSource;
import ru.otus.dalas.dao.QuestionDao;
import ru.otus.dalas.dao.QuestionDaoImpl;
import ru.otus.dalas.domain.Question;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

public class TestServiceImplTest {

    @Test
    public void startTest() throws IOException {

        Properties prop = new Properties();
        prop.load(getClass().getResourceAsStream("/config.properties"));

        MessageSource messageSource = mock(MessageSource.class);
        when(messageSource.getMessage(anyString(), any(), any())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String[] params =  (String[]) args[1];
                if (params.length == 0) {
                    return "";
                } else {
                    StringBuilder result = new StringBuilder();
                    for (String s : params) {
                        result.append(s + "/");
                    }
                    return result.toString();
                }
            }
        });

        List<Question> questions = new ArrayList<>(Arrays.asList(new Question[]{
                new Question("a", "a"),
                new Question("b", "b"),
                new Question("c", "c"),
        }));

        QuestionDao dao = mock(QuestionDaoImpl.class);
        when(dao.getQuestions()).thenReturn(questions);

        QuestionService questionService = new QuestionServiceImpl();
        ((QuestionServiceImpl) questionService).setDao(dao);

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(arrayOutputStream);
        InputStream inputStream = new ByteArrayInputStream("My\nName\na\nb\nd\n".getBytes());

        IOService ioService = new IOServiceImpl(inputStream, outputStream);

        MessageService messageService = new MessageServiceImpl(messageSource, prop.getProperty("locale.language-tag"));

        TestService testService = new TestServiceImpl(messageService, questionService, ioService);
        testService.startTest();

        assertTrue(arrayOutputStream.toString().contains("2/3"));

    }

}