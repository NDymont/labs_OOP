package com.company;

import words.WordStatisticRecoderFromFile;
import words.WriterCSVFile;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        WordStatisticRecoderFromFile file = new WordStatisticRecoderFromFile();
        file.recordWordFrequency("input.txt");
        WriterCSVFile write = new WriterCSVFile();
        write.writeCSVFile("out.csv", file.getWordFrequencies(), file.getTotalNumberOfWords());
    }
}
