import java.util.List;

/**
 * @author YonghShan
 * @date 7/31/21 - 01:48
 */
public class Solution1 {
    // DP 2D array
    /* Runtime: 2ms     O(n^2)
       Memory: 39.1MB   O(n^2)
     */
    public int minimumTotal(List<List<Integer>> tri) {
        int n = tri.size();
        int ans = Integer.MAX_VALUE;
        int[][] f = new int[n][n];
        f[0][0] = tri.get(0).get(0);
        for (int i = 1; i < n; i++) { // 第0行只有一个元素
            for (int j = 0; j < i + 1; j++) {
                int val = tri.get(i).get(j);
                // f[i][j] = Integer.MAX_VALUE;
                // if (j != 0) f[i][j] = f[i - 1][j - 1] + val;
                // 只要j在(0,i)之间，则上一步会执行，f[i][j]不再为MAX_VALUE，故这一步的min判断不可省
                // if (j != i) f[i][j] = Math.min(f[i][j], f[i - 1][j] + val);
                // 下面这么写更好懂
                if (j == 0)
                    f[i][j] = f[i - 1][j] + val;
                else if (j == i)
                    f[i][j] = f[i-1][j-1] + val;
                else
                    f[i][j] = Math.min(f[i-1][j], f[i-1][j-1]) + val;
            }
        }
        for (int i = 0; i < n; i++) ans = Math.min(ans, f[n - 1][i]);
        return ans;
    }
}
