package ru.otus.dalas.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import ru.otus.dalas.Application;

import java.io.*;

import static org.junit.Assert.assertTrue;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false"
})
public class TestServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(TestServiceImplTest.class);
    private static ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

    @Autowired
    private Shell shell;

    @Configuration
    @Import(Application.class)
    static class TestConfig {

        @Bean
        public IOService ioService() {
            InputStream inputStream = new ByteArrayInputStream("bla\n7\nbla\nbla\nbla\n".getBytes());
            PrintStream outputStream = new PrintStream(arrayOutputStream);
            return new IOServiceImpl(inputStream, outputStream);
        }

    }

    @Test
    public void startTest() throws IOException {

        shell.evaluate(() -> "start MyName");

        logger.info("---");
        logger.info(arrayOutputStream.toString());
        logger.info("---");
        assertTrue(arrayOutputStream.toString().contains("MyName"));
        assertTrue(arrayOutputStream.toString().contains("1/5"));

    }

}