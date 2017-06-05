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
public class FixedCapacityStack<Item> extends Stack<Item> {
    
    private Item[] s;
    private int N = 0;

    public FixedCapacityStack(int capacity) {
        s = (Item[]) new Object[capacity];  // the ugly cast we must do
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public void push(Item item) {
        s[N++] = item;
    }

    @Override
    public Item pop() {
        Item item = s[--N];
        s[N] = null;    // avoid 'loitering', allow the java system to free memory.
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
