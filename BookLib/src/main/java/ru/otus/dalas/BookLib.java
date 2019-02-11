package ru.otus.dalas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class BookLib {

    public static void main(String[] args) throws SQLException {

        SpringApplication.run(BookLib.class);

    }

}
