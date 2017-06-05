/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ronen Gilead-Raz <ronengi@gmail.com>
 */
public interface QueueOfStrings {

    // constructor()

    void enqueue(String item);
    String dequeue();
    boolean isEmpty();
    int size();
}
