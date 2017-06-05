/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ronen Gilead-Raz <ronengi@gmail.com>
 */
public class Stacks_And_Queues {

    /**
     * Client program. Uses "-" to pop from the stack and print.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Stack stack = new Stack();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(stack.pop());
            else
                stack.push(s);
        }
    }
    
}
