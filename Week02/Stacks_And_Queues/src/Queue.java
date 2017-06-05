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
public abstract class Queue<Item> implements Iterable<Item> {

    // constructor()

    public abstract void enqueue(Item item);
    public abstract Item dequeue();
    public abstract boolean isEmpty();
    public abstract int size();
    
    @Override
    public abstract Iterator<Item> iterator();
}
