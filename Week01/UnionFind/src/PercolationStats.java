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
import edu.princeton.cs.algs4.StdStats;


/**
 *
 * @author Ronen Gilead-Raz <ronengi@gmail.com>
 */
public class PercolationStats {
    
    private double[] thresholds;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new java.lang.IllegalArgumentException();
        this.thresholds = new double[trials]; 
        for (int test = 0; test < trials; ++test) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                perc.open(row, col);
            }
            double fraction = (double) perc.numberOfOpenSites() / (double) (n * n);
            thresholds[test] = fraction;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.thresholds);
    }

    // low  endpoint of 95% confidence interval  
    public double confidenceLo() {
        return this.mean() - (1.96 * this.stddev() / Math.sqrt(this.thresholds.length));
    }

    // high endpoint of 95% confidence interval  
    public double confidenceHi() {
        return this.mean() + (1.96 * this.stddev() / Math.sqrt(this.thresholds.length));
    }

// test client (described below)
    public static void main(String[] args) {
        if (args.length < 2)
            return;
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);

        StdOut.println("mean\t\t\t= " + ps.mean());
        StdOut.println("stddev\t\t\t= " + ps.stddev());
        StdOut.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }

}
