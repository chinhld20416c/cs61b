package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] tExperients;
    private int numTest;
    /* Perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        tExperients = new double[T];
        numTest = T;

        for (int t = 0; t < T; t++) {
            double percolatePoint;
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int randomRow = StdRandom.uniform(0, N);
                int randomCol = StdRandom.uniform(0, N);
                p.open(randomRow, randomCol);
            }
            double numOpen = p.numberOfOpenSites();
            percolatePoint = numOpen / (N * N);
            tExperients[t] = percolatePoint;
        }
    }

    /* Sample mean of percolation threshold */
    public double mean() {
        double meanPerPoint = StdStats.mean(tExperients);
        return meanPerPoint;
    }

    /* Sample standard deviation of percolation threshold */
    public double stddev() {
        if (numTest == 1) {
            return Double.NaN;
        }
        double stddevPerPoint = StdStats.stddev(tExperients);
        return stddevPerPoint;
    }

    /* Low endpoint of 95% confidence interval */
    public double confidenceLow() {
        double lowPerPoint = mean() - (1.96 * stddev()) / Math.sqrt(numTest);
        return lowPerPoint;
    }

    /* High endpoint of 95% confidence interval */
    public double confidenceHigh(){
        double highPerPoint = mean() + (1.96 * stddev()) / Math.sqrt(numTest);
        return highPerPoint;
    }
}
