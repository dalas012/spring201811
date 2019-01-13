package ru.otus.dalas.service;

import ru.otus.dalas.dao.QuestionDao;
import ru.otus.dalas.domain.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDao dao;

    public QuestionServiceImpl() {
    }

    public QuestionDao getDao() {
        return dao;
    }

    public void setDao(QuestionDao dao) {
        this.dao = dao;
    }

    public List<Question> getTest() {
        return this.dao.getQuestions();
    }
}
