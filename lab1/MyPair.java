package words;

import java.util.Objects;

public class MyPair implements Comparable<MyPair> {
    private String first;
    private Integer second;

    public MyPair(String first, Integer value) {
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
    public int compareTo(MyPair other) {
        return this.first.compareTo(other.first);
    }

    @Override
    public String toString() {
        return "{" + first + " " + second + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyPair)) return false;
        MyPair myPair = (MyPair) o;
        return Objects.equals(first, myPair.first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first);
    }

}