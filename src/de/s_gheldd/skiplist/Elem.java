package de.s_gheldd.skiplist;


import java.util.Arrays;

class Elem {
    final private long key;
    final private String data;
    Elem[] next;

    Elem(long key, String data) {
        this(key, data, null);
    }

    Elem(long key) {
        this(key, "", null);
    }

    Elem(long key, String data, final Elem[] next) {
        this.key = key;
        this.data = data;
        this.next = next;
    }

    Elem(long key, final Elem[] next) {
        this(key, "", next);
    }

    long getKey() {
        return key;
    }

    String getData() {
        return data;
    }

    void printElem() {
        System.out.print(key + ": " + data);
    }

    @Override
    public String toString() {
        return "Elem{" +
                "key=" + key +
                ", data='" + data + '\'' +
                ", next=" + Arrays.toString(next) +
                '}';
    }
}
