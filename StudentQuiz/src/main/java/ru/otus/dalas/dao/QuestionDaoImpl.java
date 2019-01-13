package ru.otus.dalas.dao;

import ru.otus.dalas.domain.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    private String fileName;
    private String separator;

    public QuestionDaoImpl(String fileName, String separator) {
        this.fileName = fileName;
        this.separator = separator;
    }

    public List<Question> getQuestions() {

        List<Question> result = new ArrayList<>();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(this.fileName).getFile());
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(this.separator);
                Question question = new Question(currentLine[0], currentLine[1]);
                result.add(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
