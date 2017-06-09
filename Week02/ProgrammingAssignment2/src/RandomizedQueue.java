
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *
 * @author Ronen Gilead-Raz <ronengi@gmail.com>
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] rdmqu;
    private int rdmquN;

    // construct an empty randomized queue   
    // @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        rdmqu = (Item[]) new Object[1];
        rdmquN = 0;
    }

    private void resize(int capacity) {
        // @SuppressWarnings("unchecked")
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < rdmquN; ++i) {
            copy[i] = rdmqu[i];
        }
        rdmqu = copy;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return rdmquN == 0;
    }

    // return the number of items on the queue
    public int size() {
        return rdmquN;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Adding null item is not allowed here.");
        }
        if (rdmquN == rdmqu.length) {
            resize(2 * rdmqu.length);
        }
        rdmqu[rdmquN++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Tried to remove an item from empty queue.");
        }
        int r = StdRandom.uniform(rdmquN);
        Item item = rdmqu[r];
        rdmqu[r] = rdmqu[--rdmquN];
        rdmqu[rdmquN] = null;
        if (rdmquN > 0 && rdmquN == rdmqu.length / 4) {
            resize(rdmqu.length / 2);
        }
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Tried to sample an item from empty queue.");
        }
        int r = StdRandom.uniform(rdmquN);
        Item item = rdmqu[r];
        return item;
    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new RQIterator();
    }


    private class RQIterator implements Iterator<Item> {

        private Item[] rdmquitr;
        private int rdmquitrN;

        // @SuppressWarnings("unchecked")
        public RQIterator() {
            rdmquitr  = (Item[]) new Object[rdmquN];
            rdmquitrN = rdmquN;
            for (int i = 0; i < rdmquitrN; ++i) {
                rdmquitr[i] = rdmqu[i];
            }
        }

        @Override
        public boolean hasNext() {
            return rdmquitrN > 0;
        }

        @Override
        public Item next() {
            if (rdmquitrN == 0) {
                throw new NoSuchElementException("Called next() on iterator which has no more elements to return.");
            }
            int r = StdRandom.uniform(rdmquitrN);
            Item item = rdmquitr[r];
            rdmquitr[r] = rdmquitr[--rdmquitrN];
            rdmquitr[rdmquitrN] = null;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Calling remove in the iterator is not allowed here.");
        }

    }


// unit testing (optional)
    public static void main(String[] args) {

        RandomizedQueue<Integer> rdq = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            rdq.enqueue(StdIn.readInt());
        }

        for (int i : rdq) {
            StdOut.println(i);
        }

        StdOut.println();

        for (int i : rdq) {
            StdOut.println(i);
        }

        StdOut.println();

        while (!rdq.isEmpty()) {
            StdOut.println(rdq.dequeue());
            StdOut.println(rdq.size());
        }
    }

}
