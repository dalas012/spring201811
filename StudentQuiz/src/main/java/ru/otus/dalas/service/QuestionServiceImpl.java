package ru.otus.dalas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dalas.dao.QuestionDao;
import ru.otus.dalas.domain.Question;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionDao dao;

    public QuestionServiceImpl() {
    }

    @Autowired
    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
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
