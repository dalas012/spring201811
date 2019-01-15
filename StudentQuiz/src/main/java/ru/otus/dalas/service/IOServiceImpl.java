package ru.otus.dalas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private InputStream inputStream;
    private PrintStream outputStream;
    private Scanner scanner;

    @Autowired
    public IOServiceImpl(InputStream inputStream, PrintStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.scanner = new Scanner(this.inputStream);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void printLine(String value) {
        outputStream.println(value);
    }

}
