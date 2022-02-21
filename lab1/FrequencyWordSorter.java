package words;

import java.util.Comparator;
import java.util.TreeSet;

public class FrequencyWordSorter {
    private TreeSet<PairStringInt> wordCounterSorted;

    public FrequencyWordSorter(TreeSet<PairStringInt> wordCounter) {
        Comparator comparator = new Comparator<PairStringInt>() {
            @Override
            public int compare(PairStringInt o1, PairStringInt o2) {
                if (o1.getSecond() == o2.getSecond()) {
                    return o1.getFirst().compareTo(o2.getFirst());
                }
                return o2.getSecond() - o1.getSecond();
            }
        };

        wordCounterSorted = new TreeSet<>(comparator);
        for (PairStringInt it : wordCounter) {
            wordCounterSorted.add(it);
        }
    }

    public TreeSet<PairStringInt> getWordCounterSorted() {
        return wordCounterSorted;
    }

}