package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Assert;

public class Percolation {
    private boolean blocked = true;
    private boolean[] gridTo1D;
    private int lenEdgeGrid;
    private WeightedQuickUnionUF uf;
    private int countOpen;  // Number of opened sites
    // Create N-by-N grid, with all sites initially blocked
    // Throw a java.lang.IllegalArgumentException if N ≤ 0
    public Percolation(int N) {
        if (N < 0){
            throw new IllegalArgumentException();
        }
        lenEdgeGrid = N;
        gridTo1D = new boolean[lenEdgeGrid * lenEdgeGrid];
        // All sites initially blocked
        for (int i = 0; i < gridTo1D.length; i++) {
            gridTo1D[i] = blocked;
        }
        uf = new WeightedQuickUnionUF(lenEdgeGrid * lenEdgeGrid);
        countOpen = 0;
    }

    /* Throw a java.lang.IndexOutOfBoundsException
    if row or column index is out of range 0 and N − 1 */
    private void validateRowCol (int r, int c) {
        if ((r < 0 || r >= lenEdgeGrid) || (c < 0 || c >= lenEdgeGrid)) {
            throw new IndexOutOfBoundsException();
        }
    }
    // Create array contains row and column coordinates
    // Turn row and col in the grid into index in array respectively
    private int xyTo1D(int row, int col) {
        return row * lenEdgeGrid + col;
    }

    // Open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateRowCol(row, col);
        int i = xyTo1D(row, col);
        gridTo1D[i] = !blocked;
        countOpen++;
        // Không ở biên phải grid
        while (col != lenEdgeGrid - 1) {
            // Nếu ô bên phải liền kề open thì union 2 ô
            if (gridTo1D[i + 1] == blocked) {
                break;
            }
            uf.union(i, i + 1);
            i = i + 1;
            col = col + 1;
        }
        // Không ở biên trái grid
        while (col != 0) {
            // Nếu ô bên trái liên kề open thì union 2 ô
            if (gridTo1D[i - 1] == blocked) {
                break;
            }
            uf.union(i, i - 1);
            i = i - 1;
            col = col - 1;
        }
        // Không ở biên trên grid
        while (row != 0) {
            // Nếu ô bên trên liền kề open thì union 2 ô
            if (gridTo1D[i - lenEdgeGrid] == blocked) {
                break;
            }
            uf.union(i, i - lenEdgeGrid);
            i = i - lenEdgeGrid;
            row = row - 1;
        }
        // Không ở biên dưới grid
        while (row != lenEdgeGrid - 1) {
            // Nếu ô bên dưới liền kề open thì union 2 ô
            if (gridTo1D[i + lenEdgeGrid] == blocked) {
                break;
            }
            uf.union(i, i + lenEdgeGrid);
            i = i + lenEdgeGrid;
            row = row + 1;
        }
    }

    // Is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateRowCol(row, col);
        int i = xyTo1D(row, col);
        return gridTo1D[i] != blocked;
    }

    // Is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateRowCol(row, col);
        int i = xyTo1D(row, col);
        for (int j = 0; j < lenEdgeGrid; j++) {
            if (gridTo1D[j] != blocked) {
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
        for (int i = 0; i < lenEdgeGrid; i++) {
            for (int j = gridTo1D.length - lenEdgeGrid; j < gridTo1D.length; j++) {
                if (gridTo1D[i] != blocked && gridTo1D[j] != blocked) {
                    if (uf.connected(i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(3,4);  // union(14, 19)
        p.open(2, 4);  // union(12, 13)
        p.open(2, 2); // union(13, 14)
        p.open(2, 3);  // union(2, 7)
        p.open(0, 2);  // union(7, 12)
        p.open(1, 2);
        boolean actual = p.isFull(2, 2);
        Assert.assertTrue(actual);
        Assert.assertTrue(p.percolates());
    }  // use for unit testing (not required, but keep this here for the autograder)

}
