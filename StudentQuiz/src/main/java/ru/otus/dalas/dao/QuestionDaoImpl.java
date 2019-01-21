package ru.otus.dalas.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import ru.otus.dalas.config.YmlProperties;
import ru.otus.dalas.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    private String fileName;
    private String separator;

    @Autowired
    public QuestionDaoImpl(YmlProperties properties) {
        this.fileName = properties.getFilename();
        this.separator = properties.getSeparator();
    }

    public List<Question> getQuestions() {

        List<Question> result = new ArrayList<>();

        String line = "";

        try {
            InputStream in = new ClassPathResource(fileName).getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(this.separator);
                if (currentLine != null && currentLine.length == 2) {
                    Question question = new Question(currentLine[0], currentLine[1]);
                    result.add(question);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
