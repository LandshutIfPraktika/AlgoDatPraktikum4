package de.s_gheldd.skiplist;

import java.util.Random;

/**
 * Created by Georg on 17.05.2016.
 */
public class SkipList implements Dictionary {

    private final int pSkipPerThousand;
    private final int lMax;
    private final Random random;
    private final Elem listStart;

    public SkipList(final int pSkipPerThousand, final int lMax) {
        this.pSkipPerThousand = pSkipPerThousand % 1000;
        this.lMax = lMax;
        this.random = new Random(System.currentTimeMillis());

        final Elem listEnd = new Elem(Long.MAX_VALUE, new Elem[lMax]);
        final Elem[] startNext = new Elem[lMax];
        for (int i = 0; i < lMax; i++) {
            startNext[i] = listEnd;
        }
        this.listStart = new Elem(Long.MIN_VALUE, startNext);
    }

    private Elem[] findShortestPath(final long key) {
        final Elem[] path = listStart.next.clone();
        Elem currentElement = listStart;
        for (int level = lMax - 1; level >= 0; level--) {
            while (currentElement.next[level].getKey() <= key) {
                currentElement = currentElement.next[level];
            }
            path[level] = currentElement;
        }
        return path;
    }

    private int calculateLevels() {
        int levels = 1;
        while (levels < lMax && random.nextInt(1000) <= pSkipPerThousand) {
            levels++;
        }
        return levels;
    }

    @Override
    public Elem search(final long key) {
        Elem[] usedPath = findShortestPath(key);
        if (usedPath[0].getKey() == key) {
            return usedPath[0];
        } else {
            return null;
        }
    }

    @Override
    public void insert(final Elem element) {
        try {


            final Elem[] usedPath = findShortestPath(element.getKey());
            final int levels = calculateLevels();
            element.next = new Elem[levels];
            for (int i = 0; i < levels; i++) {
                final Elem tmp = usedPath[i].next[i];
                usedPath[i].next[i] = element;
                element.next[i] = tmp;
            }
        }catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Elem delete(long key) {
        final Elem[] usedPath = findShortestPath(key - 1);
        final Elem deleted = usedPath[0].next[0];
        if (deleted.getKey() == key) {
            final int levels = deleted.next.length;
            for (int i = 0; i < levels; i++) {
                usedPath[i].next[i] = deleted.next[i];
            }
            deleted.next = null;
            return deleted;
        } else {
            return null;
        }
    }

    @Override
    public Elem findMin() {
        return listStart.next[0];
    }

    @Override
    public Elem findMax() {
        final Elem[] path = findShortestPath(Long.MAX_VALUE - 1);
        return path[0];
    }

    public void printListLength(){

        for (int i = lMax-1; i >= 0; i--) {
            Elem elem = listStart.next[i];
            int count = 0;
            while (elem.getKey()<Long.MAX_VALUE){
                count++;
                elem = elem.next[i];
            }
            System.out.println("Level " + (i+1) + ": " + count);
        }


    }

    public void  print() {
        StringBuilder[] lines = new StringBuilder[lMax];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new StringBuilder();
        }
        Elem elem = this.listStart.next[0];

        while (elem.getKey() < Long.MAX_VALUE) {
            for (int i = 0; i < lMax; i++) {
                lines[i].append(" -");
                if (i < elem.next.length){
                    lines[i].append(" ").append(elem.getKey());
                } else {
                    final int length = new StringBuilder().append(elem.getKey()).length();
                    lines[i].append(" ");
                    for (int j = 0; j < length; j++) {
                        lines[i].append("-");
                    }
                }
            }
            elem = elem.next[0];
        }

        for (int i = lines.length -1; i >= 0 ; i--) {
            System.out.println(lines[i].append(" -").toString());
        }

    }
}
