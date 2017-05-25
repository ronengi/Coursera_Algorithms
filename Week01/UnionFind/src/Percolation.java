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
    private int N;
    private int opened;
    

    private int siteId(int row, int col) {
        return (row - 1) * N + (col - 1);
    }
    
    private int virtualTopSite() {
        return this.N * this.N;
    }
    
    private int virtualBottomSite() {
        return this.N * this.N + 1;
    }
    
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.N = n;
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        this.grid = new int[n*n];
        for (int i = 0; i < grid.length; ++i)
            grid[i] = 0;
        this.opened = 0;
        
        // connect top and bottom rows to virtual sites N*N, N*N+1 respectively
        for (int col = 1; col <= N; ++col) {
            uf.union(siteId(1, col), virtualTopSite());     // top row
            uf.union(siteId(N, col), virtualBottomSite());  // bottom row
        }
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > N || col > N)
            throw new java.lang.IndexOutOfBoundsException("argument is outside its prescribed range");
        if (isOpen(row, col))
            return;
        int p = siteId(row, col);
        grid[p] = 1;
        this.opened += 1;

        // connect with opened neighbours
        if (row > 1 && isOpen(row-1, col))  uf.union(p, siteId(row - 1, col));
        if (row < N && isOpen(row+1, col))  uf.union(p, siteId(row + 1, col));
        if (col > 1 && isOpen(row, col-1))  uf.union(p, siteId(row, col - 1));
        if (col < N && isOpen(row, col+1))  uf.union(p, siteId(row, col + 1));
    }

     // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > N || col > N)
            throw new java.lang.IndexOutOfBoundsException("argument is outside its prescribed range");
        return grid[siteId(row, col)] == 1;
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        if (row < 1 || col < 1 || row > N || col > N)
            throw new java.lang.IndexOutOfBoundsException("argument is outside its prescribed range");
        return uf.connected(siteId(row, col), virtualTopSite());
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
        if (args.length < 2)
            return;
        int n = new Integer(args[0]);
        int t = new Integer(args[1]);
        double threshold = 0.0; 
        for (int test = 0; test < t ; ++test) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                perc.open(row, col);
            }
            double fraction = (double)perc.numberOfOpenSites() / (double)(n * n);
            threshold += fraction;
            StdOut.print(fraction);
            StdOut.println();
        }
        StdOut.print(threshold / t);
    }

}
