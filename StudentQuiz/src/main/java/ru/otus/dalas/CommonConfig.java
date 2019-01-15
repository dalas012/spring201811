package ru.otus.dalas;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;

@ComponentScan
@PropertySource("classpath:config.properties")
public class CommonConfig {

    @Bean
    public InputStream inputStream() {
        return System.in;
    }

    @Bean
    public PrintStream outputStream() {
        return System.out;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    public Locale locale(@Value("${locale}") String locale) {
        if (locale.equalsIgnoreCase("ru_RU")) {
            return new Locale("ru_RU");
        }
        return new Locale("en_US");
    }

}
