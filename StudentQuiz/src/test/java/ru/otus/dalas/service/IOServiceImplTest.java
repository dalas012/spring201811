package ru.otus.dalas.service;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class IOServiceImplTest {

    @Test
    public void readLine() {

        final String TEST_STRING = "test_string";

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(arrayOutputStream);
        InputStream inputStream = new ByteArrayInputStream(TEST_STRING.getBytes());

        IOService ioService = new IOServiceImpl(inputStream, outputStream);
        String result = ioService.readLine();

        assertEquals(TEST_STRING, result);

    }

    @Test
    public void printLine() {

        final String TEST_STRING = "test_string";

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(arrayOutputStream);
        InputStream inputStream = new ByteArrayInputStream(new byte[] {});

        IOService ioService = new IOServiceImpl(inputStream, outputStream);
        ioService.printLine(TEST_STRING);

        assertEquals(TEST_STRING + "\n", arrayOutputStream.toString());

    }
}