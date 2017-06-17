package filessort;

import java.io.File;
import java.util.Arrays;

/**
 *
 * @author Ronen Gilead-Raz <ronengi@gmail.com>
 */
public class FilesSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String dirPath = "/usr/bin";
        if (args.length > 0) {
            dirPath = args[0];
        }
        File directory = new File(dirPath);
        File[] files = directory.listFiles();
        Arrays.sort(files);
        for (int i = 0; i < files.length; ++i) {
            System.out.println(files[i].getName());
        }
    }

}
