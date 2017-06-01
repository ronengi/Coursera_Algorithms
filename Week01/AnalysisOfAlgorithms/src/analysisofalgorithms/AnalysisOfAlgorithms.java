/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analysisofalgorithms;

/**
 *
 * @author Ronen Gilead-Raz <ronengi@gmail.com>
 */
public class AnalysisOfAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    

    public static int binarySearch(int[] a, int key) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])       hi = mid - 1;
            else if (key > a[mid])  lo = mid + 1;
            else return mid;
        }
        return -1;
    }





    
}
