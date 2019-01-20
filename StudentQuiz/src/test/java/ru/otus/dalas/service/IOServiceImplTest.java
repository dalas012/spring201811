package ru.otus.dalas.service;

import org.junit.Test;

import java.io.*;
import java.util.Properties;

import static org.junit.Assert.*;

public class IOServiceImplTest {

    @Test
    public void readLine() throws IOException {

        Properties prop = new Properties();
        prop.load(getClass().getResourceAsStream("/config.properties"));

        final String TEST_STRING = prop.getProperty("test.string");

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(arrayOutputStream);
        InputStream inputStream = new ByteArrayInputStream(TEST_STRING.getBytes());

        IOService ioService = new IOServiceImpl(inputStream, outputStream);
        String result = ioService.readLine();

        assertEquals(TEST_STRING, result);

    }

    @Test
    public void printLine() throws IOException {

        Properties prop = new Properties();
        prop.load(getClass().getResourceAsStream("/config.properties"));

        final String TEST_STRING = prop.getProperty("test.string");

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(arrayOutputStream);
        InputStream inputStream = new ByteArrayInputStream(new byte[] {});

        IOService ioService = new IOServiceImpl(inputStream, outputStream);
        ioService.printLine(TEST_STRING);

        assertEquals(TEST_STRING + "\n", arrayOutputStream.toString());

    }
}