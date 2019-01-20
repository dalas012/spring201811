package ru.otus.dalas.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import ru.otus.dalas.domain.Question;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Repository
@PropertySource("classpath:config.properties")
public class QuestionDaoImpl implements QuestionDao {

    private String fileName;
    private String separator;

    public QuestionDaoImpl(@Value("${locale}") String locale,
                           @Value("${quiz.filename}") String fileName,
                           @Value("${quiz.filename_ru_RU}") String fileNameRu,
                           @Value("${quiz.separator}") String separator) {
        if (locale.equalsIgnoreCase("default")) {
            this.fileName = fileName;
        } else if (locale.equalsIgnoreCase("ru_RU")) {
            this.fileName = fileNameRu;
        }
        this.separator = separator;
    }

    public List<Question> getQuestions() {

        List<Question> result = new ArrayList<>();

        String line = "";

        try {
            String decodedFileName = URLDecoder.decode(fileName, "UTF-8");
            String file = getClass().getClassLoader().getResource(decodedFileName).getFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
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
