/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ronen Gilead-Raz <ronengi@gmail.com>
 */
public class ResizingArrayStackOfStrings {
    
    private String[] s;
    private int N = 0;


    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; ++i)
            copy[i] = s[i];
        s = copy;
    }

    public ResizingArrayStackOfStrings() {
        s = new String[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * If array is full, resize it to double its current size.
     * @param item 
     */
    public void push(String item) {
        if (N == s.length)
            resize(2 * s.length);
        s[N++] = item;
    }

    /**
     * If array is 1/4 full, resize it to 1/2 its current size.
     * Avoid 'loitering', allow the java system to free memory. 
     * @return 
     */
    public String pop() {
        String item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length / 4)
            resize(s.length / 2);
        return item;
    }

}
