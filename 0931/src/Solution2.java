/**
 * @author YonghShan
 * @date 8/3/21 - 01:25
 */
public class Solution2 {
    // 按照常规的DP问题求解
    /* Runtime: 4ms (faster than 47.63%)   O(n^2)
       Memory: 39.7MB (less than 41.50%)   O(n^2)
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] f = new int[n][n];
        // 初始化：对于首行而言，每个位置的「最小成本」就是其「矩阵值」
        for (int i = 0; i < n; i++) f[0][i] = matrix[0][i];
        // 从第二行开始，根据题目给定的条件进行转移
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = matrix[i][j];
                f[i][j] = f[i - 1][j] + val;
                if (j - 1 >= 0) f[i][j] = Math.min(f[i][j], f[i-1][j-1] + val);
                if (j + 1 <  n) f[i][j] = Math.min(f[i][j], f[i-1][j+1] + val);
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) ans = Math.min(ans, f[n-1][i]);
        return ans;
    }
}
