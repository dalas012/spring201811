package ru.otus.dalas.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.dalas.service.IOService;
import ru.otus.dalas.service.IOServiceImpl;

@Configuration
public class CommonConfig {

    @Bean
    public IOService ioService() {
        return new IOServiceImpl(System.in, System.out);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

}
