package ru.otus.dalas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.dalas.config.YmlProperties;
import ru.otus.dalas.service.TestService;


@SpringBootApplication
@EnableConfigurationProperties(YmlProperties.class)
public class Application implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    TestService testService;

    public static void main(String[] args) {

        logger.info(" --- Start application --- ");
        SpringApplication.run(Application.class);
        logger.info(" --- End application --- ");

    }

    @Override
    public void run(String... args) throws Exception {
        testService.startTest();
    }

}
