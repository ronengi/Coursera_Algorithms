import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author Ronen Gilead-Raz <ronengi@gmail.com>
 */
public class Permutation {
    
    public static  void main(String[] args) throws FileNotFoundException {
        FileInputStream is = new FileInputStream(new File("Permutation.text"));
        System.setIn(is);

        int k = StdIn.readInt();
        RandomizedQueue<String> rdq = new RandomizedQueue<>();
        while (!StdIn.isEmpty())
            rdq.enqueue(StdIn.readString());

        while (k > 0 && !rdq.isEmpty()) {
            StdOut.println(rdq.dequeue());
            k--;
        }
    }
}
