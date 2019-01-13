package ru.otus.dalas.dao;

import ru.otus.dalas.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> getQuestions();

}
