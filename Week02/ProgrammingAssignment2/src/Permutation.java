import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Ronen Gilead-Raz <ronengi@gmail.com>
 */
public class Permutation {
    
    public static  void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rdq = new RandomizedQueue<>();
        while (!StdIn.isEmpty())
            rdq.enqueue(StdIn.readString());

        while (k > 0 && !rdq.isEmpty()) {
            StdOut.println(rdq.dequeue());
            k--;
        }
    }
}
