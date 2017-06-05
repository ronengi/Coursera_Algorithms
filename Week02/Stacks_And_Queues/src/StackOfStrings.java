/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ronen Gilead-Raz <ronengi@gmail.com>
 */
public interface StackOfStrings {
    // constructor to create am empty stack
    void push(String item);
    String pop();
    boolean isEmpty();
    int size();
}
