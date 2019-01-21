package ru.otus.dalas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.dalas.config.YmlProperties;

import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService {

    private Locale locale;
    private MessageSource messageSource;
    private String[] emptyParams;

    @Autowired
    public MessageServiceImpl(
            MessageSource messageSource,
            YmlProperties properties) {
        this.messageSource = messageSource;
        this.locale = new Locale(properties.getLanguageTag());
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
