package ru.otus.dalas.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class YmlProperties {

    private String filename;
    private String separator;
    private String languageTag;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getLanguageTag() {
        return languageTag;
    }

    public void setLanguageTag(String languageTag) {
        this.languageTag = languageTag;
    }
}
