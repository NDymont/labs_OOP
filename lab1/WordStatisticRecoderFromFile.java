package words;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.util.TreeSet;

public class WordStatisticRecoderFromFile {
    private TreeSet<PairStringInt> wordCounter;
    private Integer totalNumberOfWords;

    public WordStatisticRecoderFromFile() {
        wordCounter = new TreeSet<PairStringInt>();
        totalNumberOfWords = 0;
    }

    public TreeSet<PairStringInt> getWordFrequencies() {
        return wordCounter;
    }

    private void addWord(String word) {
        if (word.length() > 0) {
            PairStringInt pair = new PairStringInt(word, 1);
            if (wordCounter.contains(pair)) {
                wordCounter.tailSet(pair).first().setSecond(
                        wordCounter.tailSet(pair).first().getSecond() + 1);
            } else {
                wordCounter.add(pair);
            }
            totalNumberOfWords++;
        }
    }

    public void recordWordFrequency(String fileName) throws IOException {

        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName), 200);
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        StringBuilder buffer = new StringBuilder("");
        int i;
        try {
            while ((i = bufferedInputStream.read()) != -1) {
                if (Character.isLetterOrDigit(i)) {
                    buffer = buffer.append((char) i);
                } else {
                    addWord(String.valueOf(buffer));
                    buffer.setLength(0);
                }
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        } finally {
            bufferedInputStream.close();
        }
        addWord(String.valueOf(buffer));
        buffer.setLength(0);
    }

    public Integer getTotalNumberOfWords() {
        return totalNumberOfWords;
    }
}
