package words;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

public class WriterCSVFile {
    Character delimiter;

    public WriterCSVFile() {
        delimiter = ';';
    }

    public void writeCSVFile(String filePath, Collection<PairStringInt> wordCounter, Integer wordNumber) throws IOException {
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(filePath);
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        FrequencyWordSorter frequencyWordSorter = new FrequencyWordSorter(wordCounter);
        try {
            for (PairStringInt it : frequencyWordSorter.getWordCounterSorted()) {
                output.write((it.getFirst() + delimiter + it.getSecond() + delimiter +
                        String.format("%.1f", (double) it.getSecond() * 100 / wordNumber) + "%\n").getBytes());
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        } finally {
            output.close();
        }

    }

    public void setDelimiter(Character delimiter) {
        this.delimiter = delimiter;
    }
}
