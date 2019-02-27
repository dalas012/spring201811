package ru.otus.dalas.configs;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoBeeConfig {

    private MongoClient mongo;
    private MongoTemplate template;

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Autowired
    public MongoBeeConfig(MongoClient mongo, MongoTemplate template) {
        this.mongo = mongo;
        this.template = template;
    }

    @Bean
    public Mongobee mongobee(Environment environment) {
        Mongobee runner = new Mongobee(mongo);
        runner.setDbName(dbName);
        runner.setChangeLogsScanPackage("ru.otus.dalas.changelogs");
        runner.setSpringEnvironment(environment);
        runner.setMongoTemplate(template);
        return runner;
    }

}
