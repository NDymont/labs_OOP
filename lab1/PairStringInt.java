package words;

import java.util.Objects;

public class PairStringInt implements Comparable<PairStringInt> {
    private String first;
    private Integer second;

    public PairStringInt(String first, Integer value) {
        this.first = first;
        this.second = value;
    }

    public void setSecond(Integer value) {
        this.second = value;
    }

    public Integer getSecond() {
        return second;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String value) {
        this.first = value;
    }

    @Override
    public int compareTo(PairStringInt other) {
        return this.first.compareTo(other.first);
    }

    @Override
    public String toString() {
        return "{" + first + " " + second + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairStringInt that = (PairStringInt) o;
        return getFirst().equals(that.getFirst()) && getSecond().equals(that.getSecond());
    }

    @Override
    public int hashCode() {
        return Objects.hash(first);
    }

}