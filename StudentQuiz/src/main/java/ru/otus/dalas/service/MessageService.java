package ru.otus.dalas.service;

public interface MessageService {

    String getMessage(String messageKey, String[] params);

    String getMessage(String messageKey);

}
