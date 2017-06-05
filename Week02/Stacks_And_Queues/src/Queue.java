/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ronen Gilead-Raz <ronengi@gmail.com>
 * @param <Item>
 */
public interface Queue<Item> {

    // constructor()

    void enqueue(Item item);
    Item dequeue();
    boolean isEmpty();
    int size();
}
