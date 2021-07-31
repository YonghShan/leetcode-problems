import java.util.Arrays;

/**
 * @author YonghShan
 * @date 7/29/21 - 15:40
 */
public class Solution1 {
    // DP 2D Array
    /* Runtime: 0ms
       Memory: 35.9MB (less than 53.52%)  O(m x n)
     */
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        f[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && j > 0) {
                    f[i][j] = f[i][j-1] + f[i-1][j];
                } else if (j > 0) {
                    f[i][j] = f[i][j-1];
                } else if (i > 0) {
                    f[i][j] = f[i-1][j];
                }
            }
        }
        return f[m-1][n-1];
    }

    public int uniquePaths2(int m, int n) {
        int[][] f = new int[m][n];
        for(int[] arr : f) Arrays.fill(arr, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[i][j] = f[i][j-1] + f[i-1][j];
            }
        }
        return f[m-1][n-1];
    }
}
