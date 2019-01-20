package ru.otus.dalas.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import ru.otus.dalas.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
@PropertySource("classpath:config.properties")
public class QuestionDaoImpl implements QuestionDao {

    private String fileName;
    private String separator;

    public QuestionDaoImpl(@Value("${quiz.filename}") String fileName,
                           @Value("${quiz.separator}") String separator) {
        this.fileName = fileName;
        this.separator = separator;
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
