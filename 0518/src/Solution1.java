/**
 * @author YonghShan
 * @date 9/4/21 - 00:37
 */
public class Solution1 {
    // 完全背包
    // 和[0322]一样
    /* Runtime: 101ms (faster than 7.13%)  O(n*amount^2)
       Memory: 48.2MB (less than 8.71%)   O(n*amount)
     */
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] f = new int[n+1][amount+1];
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int val = coins[i-1];
            for (int c = 0; c <= amount; c++) {
                // 「不选」：
                f[i][c] = f[i-1][c];
                // 「选」：
                for (int k = 1; k * val <= c; k++)
                    f[i][c] += f[i-1][c-k*val];
            }
        }
        return f[n][amount];
    }
}
