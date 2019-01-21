package ru.otus.dalas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@PropertySource("classpath:config.properties")
public class MessageServiceImpl implements MessageService {

    private Locale locale;
    private MessageSource messageSource;
    private String[] emptyParams;

    @Autowired
    public MessageServiceImpl(
            MessageSource messageSource,
            @Value("${locale.language-tag}") String languageTag) {
        this.messageSource = messageSource;
        this.locale = new Locale(languageTag);
        this.emptyParams = new String[0];
    }

    @Override
    public String getMessage(String messageKey, String[] params) {
        return messageSource.getMessage(messageKey, params, locale);
    }

    @Override
    public String getMessage(String messageKey) {
        return messageSource.getMessage(messageKey, emptyParams, locale);
    }
}
