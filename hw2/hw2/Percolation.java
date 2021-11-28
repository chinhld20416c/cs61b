package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Assert;
import edu.princeton.cs.algs4.In;

public class Percolation {
    private boolean blocked = true;
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private int countOpen;  // Number of opened sites

    // Create N-by-N grid, with all sites initially blocked
    // Throw a java.lang.IllegalArgumentException if N ≤ 0
    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        grid = new boolean[N][N];
        // All sites initially blocked
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = blocked;
            }
        }
        uf = new WeightedQuickUnionUF(N * N);
        countOpen = 0;
    }

    /* Throw a java.lang.IndexOutOfBoundsException
    if row or column index is out of range 0 and N − 1 */
    private void validateRowCol(int r, int c) {
        if ((r < 0 || r >= grid.length) || (c < 0 || c >= grid.length)) {
            throw new IndexOutOfBoundsException();
        }
    }

    // Turn row and col in the grid into index in an array respectively
    private int xyTo1D(int row, int col) {
        return row * grid.length + col;
    }

    // Open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateRowCol(row, col);
        if (grid[row][col] != blocked) {  // Not count open if it already open
            return;
        }

        grid[row][col] = !blocked;
        countOpen++;

        /* Union next to the site in left, right, top or bottom */
        int i = xyTo1D(row, col);  // row and column coordinates
        /* Very dangerous when calculating directly on row and col,
        unwanted row or col in the next union */
        /* Change while into if */

        // Không ở biên phải grid
        if (col != grid.length - 1) {
            // Nếu ô bên phải liền kề open thì union 2 ô
            if (grid[row][col + 1] != blocked) {
                uf.union(i, i + 1);
            }
//            i = i + 1;
//            col = col + 1;
        }

        // Không ở biên trái grid
        if (col != 0) {
            // Nếu ô bên trái liên kề open thì union 2 ô
            if (grid[row][col - 1] != blocked) {
                uf.union(i, i - 1);
            }
//            i = i - 1;
//            col = col - 1;
        }

        // Không ở biên trên grid
        if (row != 0) {
            // Nếu ô bên trên liền kề open thì union 2 ô
            if (grid[row - 1][col] != blocked) {
                uf.union(i, i - grid.length);
            }
//            i = i - grid.length;
//            row = row - 1;
        }

        // Không ở biên dưới grid
        if (row != grid.length - 1) {
            // Nếu ô bên dưới liền kề open thì union 2 ô
            if (grid[row + 1][col] != blocked) {
                uf.union(i, i + grid.length);
            }
//            i = i + grid.length;
//            row = row + 1;
        }
    }

    // Is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateRowCol(row, col);
        return grid[row][col] != blocked;
    }

    // Is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateRowCol(row, col);
        int i = xyTo1D(row, col);
        for (int c = 0; c < grid.length; c++) {
            if (grid[0][c] != blocked) {
                int j = xyTo1D(0, c);
                if (uf.connected(j, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Number of open sites
    public int numberOfOpenSites() {
        return countOpen;
    }

    // Does the system percolate?
    public boolean percolates() {
        for (int c = 0; c < grid.length; c++) {
            if (grid[grid.length - 1][c] != blocked) {  // Continute if a site in the bottom not open
                if (isFull(grid.length - 1, c)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Percolation p = new Percolation(n);
        while (!in.isEmpty()) {
            int r = in.readInt();
            int c = in.readInt();
            p.open(r, c);
        }
        Assert.assertTrue(p.percolates());

    }  // use for unit testing (not required, but keep this here for the autograder)

}
