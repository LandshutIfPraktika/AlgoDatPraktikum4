package de.s_gheldd.skiplist;

/**
 * Created by Georg on 18.05.2016.
 */
public class Main {
    public static void main(String[] args) {
        final SkipList list = new SkipList(333, 14);

        for (int i = 0; i < 1000000; i++) {
            list.insert(new Elem((3+1)*i));
        }
        
        list.printListLength();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.search(3*i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");

    }
}
