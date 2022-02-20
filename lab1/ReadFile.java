package words;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.util.TreeSet;

public class ReadFile {
    private TreeSet<MyPair> wordCounter;
    private Integer totalNumberOfWords;

    public ReadFile() {
        wordCounter = new TreeSet<MyPair>();
        totalNumberOfWords = 0;
    }

    public TreeSet<MyPair> getWordCounter() {
        return wordCounter;
    }

    private void addWord(String word) {
        if (word.length() > 0) {
            MyPair pair = new MyPair(word, 1);
            if (wordCounter.contains(pair)) {
                wordCounter.tailSet(pair).first().setSecond(
                        wordCounter.tailSet(pair).first().getSecond() + 1);
            } else {
                wordCounter.add(pair);
            }
            totalNumberOfWords++;
        }
    }

    public void fileToSet(String fileName) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 200);
        StringBuilder buffer = new StringBuilder("");
        int i;
        while ((i = bufferedInputStream.read()) != -1) {
            if (Character.isLetterOrDigit(i)) {
                buffer = buffer.append((char) i);
            } else {
                addWord(String.valueOf(buffer));
                buffer.setLength(0);
            }
        }
        addWord(String.valueOf(buffer));
        buffer.setLength(0);
    }

    public Integer getTotalNumberOfWords() {
        return totalNumberOfWords;
    }
}
