package words;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.TreeSet;

public class WriterCSVFile {
    Character delimiter;

    public WriterCSVFile() {
        delimiter = ';';
    }

    public void writeCSVFile(String filePath, TreeSet<PairStringInt> wordCounter, Integer wordNumber) throws FileNotFoundException {
        PrintWriter output = null;
        try {
            output = new PrintWriter(filePath);
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        FrequencyWordSorter frequencyWordSorter = new FrequencyWordSorter(wordCounter);
        for (PairStringInt it : frequencyWordSorter.getWordCounterSorted()) {
            output.write(it.getFirst() + delimiter + it.getSecond() + delimiter +
                    String.format("%.3f", (double) it.getSecond() * 100 / wordNumber) + "%\n");
        }
        output.close();
    }

    public void setDelimiter(Character delimiter) {
        this.delimiter = delimiter;
    }
}
