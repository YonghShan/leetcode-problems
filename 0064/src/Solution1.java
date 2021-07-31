/**
 * @author YonghShan
 * @date 7/31/21 - 00:17
 */
public class Solution1 {
    // DP 2D array    就不写1D DP了。。
    /* Runtime: 3ms (faster than 30.40%)   O(m x n)
       Memory: 41.9MB (less than 45.28%)   O(m x n)
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    f[i][j] = grid[i][j];
                } else {
                    int top  = i - 1 >= 0 ? f[i - 1][j] + grid[i][j] : Integer.MAX_VALUE; // 第一行没有top，即为MAX_VALUE
                    int left = j - 1 >= 0 ? f[i][j - 1] + grid[i][j] : Integer.MAX_VALUE; // 第一列没有left，即为MAX_VALUE
                    f[i][j] = Math.min(top, left);
                }
            }
        }
        return f[m - 1][n - 1];
    }
}
