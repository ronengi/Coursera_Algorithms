/*
 * Copyright 2017 Ronen Gilead-Raz <ronengi@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


/**
 *
 * @author Ronen Gilead-Raz <ronengi@gmail.com>
 */
public class Percolation {

    private int [] grid;
    private WeightedQuickUnionUF uf;
    private int gridN;
    private int opened;


    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.gridN = n;
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        this.grid = new int[n*n];
        for (int i = 0; i < grid.length; ++i)
            grid[i] = 0;
        this.opened = 0;
    }

    private int siteId(int row, int col) {
        return (row - 1) * gridN + (col - 1);
    }

    private int virtualTopSite() {
        return this.gridN * this.gridN;
    }

    private int virtualBottomSite() {
        return this.gridN * this.gridN + 1;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > gridN || col > gridN)
            throw new java.lang.IndexOutOfBoundsException();
        if (isOpen(row, col))
            return;
        int p = siteId(row, col);
        this.grid[p] = 1;
        this.opened += 1;

        // connect with opened neighbours
        if (row > 1 && isOpen(row - 1, col))      uf.union(p, siteId(row - 1, col));
        if (row < gridN && isOpen(row + 1, col))  uf.union(p, siteId(row + 1, col));
        if (col > 1 && isOpen(row, col - 1))      uf.union(p, siteId(row, col - 1));
        if (col < gridN && isOpen(row, col + 1))  uf.union(p, siteId(row, col + 1));


        // connect opened top and bottom rows sites to virtual sites
        if (row == 1)
            uf.union(p, virtualTopSite());     // top row
        if (row == gridN) // && this.isFull(row, col))
            uf.union(p, virtualBottomSite());  // bottom row
    }

     // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > gridN || col > gridN)
            throw new java.lang.IndexOutOfBoundsException();
        return grid[siteId(row, col)] == 1;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > gridN || col > gridN)
            throw new java.lang.IndexOutOfBoundsException();
        if (isOpen(row, col))
            return uf.connected(siteId(row, col), virtualTopSite());
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return this.opened;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(virtualTopSite(), virtualBottomSite());
    }


    public static void main(String[] args) {  // test client (optional)
        int n = 20;
        Percolation perc = new Percolation(n);

        // perc.open(18, 1);
        // StdOut.println(perc.isOpen(18, 1));
        // StdOut.println(perc.percolates());
        // StdOut.println(perc.numberOfOpenSites());
        // StdOut.println(perc.isFull(18, 1));
        // StdOut.println();



//        StdOut.println(perc.isFull(1, 1));
//        perc.open(1, 1);
//        StdOut.println(perc.isFull(1, 1));
//        StdOut.println(perc.percolates());

        while (!perc.percolates()) {
            int row = StdRandom.uniform(1, n+1);
            int col = StdRandom.uniform(1, n+1);
            perc.open(row, col);
        }
        double fraction = (double) perc.numberOfOpenSites() / (double) (n * n);
            StdOut.println(fraction);
    }

}
