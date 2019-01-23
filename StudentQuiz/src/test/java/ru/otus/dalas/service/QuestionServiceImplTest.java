package ru.otus.dalas.service;

import org.junit.jupiter.api.Test;
import ru.otus.dalas.dao.QuestionDao;
import ru.otus.dalas.dao.QuestionDaoImpl;
import ru.otus.dalas.domain.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionServiceImplTest {

    @Test
    public void getTest() {

        List<Question> questions = new ArrayList<>(Arrays.asList(new Question[]{
                new Question("a", "a"),
                new Question("b", "b"),
                new Question("c", "c"),
        }));

        QuestionDao dao = mock(QuestionDaoImpl.class);
        when(dao.getQuestions()).thenReturn(questions);

        QuestionService questionService = new QuestionServiceImpl();
        ((QuestionServiceImpl) questionService).setDao(dao);

        List<Question> result = questionService.getTest();
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(questions, result);

    }

}