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
public class LinkedQueue<Item> extends Queue<Item> {
    
    private Node first, last;

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class Node {
        Item item;
        Node next;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
    }

    @Override
    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        return item;
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    public class ListIterator implements Iterator<Item> {
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current == null;
        }
        
        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
