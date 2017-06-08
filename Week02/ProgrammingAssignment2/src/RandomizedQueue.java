import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *
 * @author Ronen Gilead-Raz <ronengi@gmail.com>
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] rq;
    private int N;
    
    // construct an empty randomized queue   
    public RandomizedQueue() {
        rq = (Item[]) new Object[1];
        N = 0;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; ++i)
            copy[i] = rq[i];
        rq = copy;
    }
    
    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException("Adding null item is not allowed here.");
        if (N == rq.length)
            resize(2 * rq.length);
        rq[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Tried to remove an item from empty queue.");
        int r = StdRandom.uniform(N);
        Item item = rq[r];
        rq[r] = rq[--N];
        rq[N] = null;
        if (N > 0 && N == rq.length / 4)
            resize(rq.length / 2);
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Tried to sample an item from empty queue.");
        int r = StdRandom.uniform(N);
        Item item = rq[r];
        return item;
    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new RQIterator();
    }


    public class RQIterator implements Iterator<Item> {
        private Item[] rqi;
        private int Ni;

        public RQIterator() {
             rqi = (Item[]) new Object[N];
             Ni = N;
             for (int i = 0; i < Ni; ++i)
                 rqi[i] = rq[i];
        }
        
        @Override
        public boolean hasNext() {
            return Ni > 0;
        }

        @Override
        public Item next() {
            if (Ni == 0)
                throw new NoSuchElementException("Called next() on iterator which has no more elements to return.");
            int r = StdRandom.uniform(Ni);
            Item item = rqi[r];
            rqi[r] = rqi[--Ni];
            rqi[Ni] = null;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Calling remove in the iterator is not allowed here.");
        }

    }

    
// unit testing (optional)
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream is = new FileInputStream(new File("input2.txt"));
        System.setIn(is);
        
        RandomizedQueue<Integer> rdq = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            rdq.enqueue(StdIn.readInt());
        }

        for (int i : rdq)
            StdOut.println(i);

        StdOut.println();

        for (int i : rdq)
            StdOut.println(i);
        
        StdOut.println();
        
        while (!rdq.isEmpty()) {
            StdOut.println(rdq.dequeue());
            StdOut.println(rdq.size());
        }
    }

}
