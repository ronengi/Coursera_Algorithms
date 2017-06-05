/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Iterator;

/**
 *
 * @author Ronen Gilead-Raz <ronengi@gmail.com>
 * @param <Item>
 */
public class ResizingArrayStack<Item> extends Stack<Item> {
    
    private Item[] s;
    private int N = 0;

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; ++i)
            copy[i] = s[i];
        s = copy;
    }

    public ResizingArrayStack() {
        s = (Item[]) new Object[1];
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * If array is full, resize it to double its current size.
     * @param item 
     */
    @Override
    public void push(Item item) {
        if (N == s.length)
            resize(2 * s.length);
        s[N++] = item;
    }

    /**
     * If array is 1/4 full, resize it to 1/2 its current size.
     * Avoid 'loitering', allow the java system to free memory. 
     * @return 
     */
    @Override
    public Item pop() {
        Item item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length / 4)
            resize(s.length / 2);
        return item;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
           return s[--i];
        }

    }

}
