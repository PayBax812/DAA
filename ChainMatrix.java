import java.util.Arrays;

public class MatrixChainMultiplication {

    // Function to compute the minimum number of multiplications needed
    public static void matrixChainOrder(int[] p) {
        int n = p.length - 1; // Number of matrices
        int[][] m = new int[n][n]; // m[i][j] is the minimum number of multiplications needed to compute the product of matrices Ai...Aj
        int[][] s = new int[n][n]; // s[i][j] is the index of the matrix after which the product is split

        // l is the chain length
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE; // Initialize to a large number
                for (int k = i; k < j; k++) {
                    // q is the cost of multiplying the matrices Ai...Ak and Ak+1...Aj
                    int q = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k; // Store the index of the split
                    }
                }
            }
        }

        // Print the result
        System.out.println("Minimum number of multiplications is " + m[0][n - 1]);
        System.out.println("Optimal Parenthesization is: ");
        printOptimalParenthesis(s, 0, n - 1);
    }

    // Function to print the optimal parenthesization
    public static void printOptimalParenthesis(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("A" + (i + 1));
        } else {
            System.out.print("(");
            printOptimalParenthesis(s, i, s[i][j]);
            printOptimalParenthesis(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        // Dimensions of matrices
        // For example, if we have matrices A1 (10x20), A2 (20x30), A3 (30x40), A4 (40x30)
        int[] dimensions = {40, 20, 30, 10, 30}; // 4 matrices A1, A2, A3, A4

        matrixChainOrder(dimensions);
    }
}
