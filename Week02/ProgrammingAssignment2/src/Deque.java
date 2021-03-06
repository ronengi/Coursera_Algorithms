import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


/**
 *
 * @author Ronen Gilead-Raz <ronengi@gmail.com>
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int dqSize;
    
    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        dqSize = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return dqSize;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
                throw new NullPointerException("null items are not allowed here.");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        first.next = oldFirst;
        if (oldFirst == null)
            last = first;
        else
            oldFirst.prev = first;
        dqSize++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null)
                throw new NullPointerException("null items are not allowed here.");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if (oldLast == null)
            first = last;
        else
            oldLast.next = last;
        dqSize++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Tried to remove an item from an empty dequeue.");
        Item item = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        else
            first.prev = null;
        dqSize--;
        return item;
    }
    
    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Tried to remove an item from an empty dequeue.");
        Item item = last.item;
        last = last.prev;
        if (last == null)
            first = null;
        else
            last.next = null;
        dqSize--;
        return item;
    }

    
    private class Node {
        Item item;
        Node next;
        Node prev;
    }


    // return an iterator over items in order from front to end
    @Override
    public Iterator<Item> iterator() {
        return new DQIterator();
    }


    private class DQIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (isEmpty())
                throw new NoSuchElementException("Called next() on iterator which has no more elements to return.");
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Calling remove in the iterator is not allowed here.");
        }
        
    }
    
    // unit testing (optional)
    public static void main(String[] args) {

        Deque<String> dq = new Deque<>();
        
        while (!StdIn.isEmpty()) {
            dq.addLast(StdIn.readString());
        }

        for (String s : dq) {
            StdOut.println(s);
        }
        
        
        StdOut.println();
            
        while (!dq.isEmpty()) {
            StdOut.println(dq.removeLast());
        }
    }

}
