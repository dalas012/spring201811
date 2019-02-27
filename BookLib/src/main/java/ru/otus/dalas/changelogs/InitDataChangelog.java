package ru.otus.dalas.changelogs;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.dalas.model.Author;
import ru.otus.dalas.model.Book;

@ChangeLog
public class InitDataChangelog {

    @ChangeSet(order = "001", id = "addData", author = "dalay")
    public void insertInitialData(MongoTemplate mongoTemplate) {
        Author asimov = mongoTemplate.save(new Author("Isaac Asimov"));
        Author london = mongoTemplate.save(new Author("Jack London"));
        mongoTemplate.save(new Book("Bicentennial man", asimov, "Fantasy"));
        mongoTemplate.save(new Book("White Fang", london, "Adventure"));
    }

}
