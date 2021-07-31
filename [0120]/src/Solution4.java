import java.util.List;

/**
 * @author YonghShan
 * @date 7/31/21 - 01:49
 */
public class Solution4 {
    // 在原代码上直接将其中一维的维度改为2，任何在该维的 `f[i]` 改成 `f[i&1]` 或者 `f[i%2]` 即可（推荐前者，在不同架构的机器上，运算效率更加稳定）
    /* Runtime: 3ms
       Memory: 39.1MB (less than 48.2%)   O(2n) = O(n)
     */
    public int minimumTotal(List<List<Integer>> tri) {
        int n = tri.size();
        int ans = Integer.MAX_VALUE;
        int[][] f = new int[2][n];
        f[0][0] = tri.get(0).get(0);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                int val = tri.get(i).get(j);
                f[i & 1][j] = Integer.MAX_VALUE;
                if (j != 0) f[i & 1][j] = Math.min(f[i & 1][j], f[(i - 1) & 1][j - 1] + val);
                if (j != i) f[i & 1][j] = Math.min(f[i & 1][j], f[(i - 1) & 1][j] + val);
            }
        }
        for (int i = 0; i < n; i++) ans = Math.min(ans, f[(n - 1) & 1][i]);
        return ans;
    }
}
