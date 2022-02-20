package words;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.TreeSet;

public class WriteCSVFile {
    Character delimiter;
    private PrintWriter output;
    public WriteCSVFile(String filePath) throws FileNotFoundException {
        delimiter = ';';
        output = new PrintWriter(filePath);
    }

    public void writeCSVFile(TreeSet<MyPair> wordCounter, Integer wordNumber) {
        Sort sort = new Sort(wordCounter);
        for (MyPair it : sort.getWordCounterSorted()) {
            output.write(it.getFirst() + delimiter + it.getSecond() + delimiter +
                    String.format("%.3f", (double)it.getSecond() * 100 / wordNumber) + "%\n");
        }
        output.close();
    }

    public void setDelimiter(Character delimiter) {
        this.delimiter = delimiter;
    }
}
