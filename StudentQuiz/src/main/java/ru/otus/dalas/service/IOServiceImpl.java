package ru.otus.dalas.service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceImpl implements IOService {

    private InputStream inputStream;
    private PrintStream outputStream;
    private Scanner scanner;

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
