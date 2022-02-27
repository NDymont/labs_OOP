package words;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

public class FrequencyWordSorter {
    private TreeSet<PairStringInt> wordCounterSorted;

    public FrequencyWordSorter(Collection<PairStringInt> wordCounter) {

        Comparator<PairStringInt> comparator = new Comparator<PairStringInt>() {
            @Override
            public int compare(PairStringInt o1, PairStringInt o2) {
                if (o1.getSecond().equals(o2.getSecond())) {
                    return o1.getFirst().compareTo(o2.getFirst());
                }
                return o2.getSecond().compareTo(o1.getSecond());
            }
        };

        wordCounterSorted = new TreeSet<PairStringInt>(comparator);
        wordCounterSorted.addAll(wordCounter);
    }

    public Collection<PairStringInt> getWordCounterSorted() {
        return wordCounterSorted;
    }

}