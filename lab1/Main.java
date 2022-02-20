package com.company;

import words.ReadFile;
import words.WriteCSVFile;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ReadFile file = new ReadFile();
        file.fileToSet("input.txt");
        WriteCSVFile write = new WriteCSVFile("out.csv");
        write.writeCSVFile(file.getWordCounter(), file.getTotalNumberOfWords());
    }
}
