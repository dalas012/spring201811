package ru.otus.dalas.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.dalas.Application;

import java.io.*;

import static org.junit.Assert.assertTrue;

@SpringBootTest
public class TestServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(TestServiceImplTest.class);
    private static ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

    @Configuration
    @Import(Application.class)
    static class TestConfig {

        @Bean
        public IOService ioService() {
            InputStream inputStream = new ByteArrayInputStream("My\nName\nbla\n7\nbla\nbla\nbla\n".getBytes());
            PrintStream outputStream = new PrintStream(arrayOutputStream);
            return new IOServiceImpl(inputStream, outputStream);
        }

    }

    @Test
    public void startTest() throws IOException {

        logger.info("---");
        logger.info(arrayOutputStream.toString());
        logger.info("---");
        assertTrue(arrayOutputStream.toString().contains("My Name"));
        assertTrue(arrayOutputStream.toString().contains("1/5"));

    }

}