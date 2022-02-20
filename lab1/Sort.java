package words;

import java.util.Comparator;
import java.util.TreeSet;

public class Sort implements Comparator<MyPair> {
    private TreeSet<MyPair> wordCounterSorted;

    public Sort(TreeSet<MyPair> wordCounter) {
        wordCounterSorted = new TreeSet<>(this::compare);
        for (MyPair it : wordCounter) {
            wordCounterSorted.add(it);
        }
    }

    public TreeSet<MyPair> getWordCounterSorted() {
        return wordCounterSorted;
    }

    @Override
    public int compare(MyPair o1, MyPair o2) {
        if (o1.getSecond() == o2.getSecond()) {
            return o1.getFirst().compareTo(o2.getFirst());
        }
        return o2.getSecond() - o1.getSecond();
    }
}