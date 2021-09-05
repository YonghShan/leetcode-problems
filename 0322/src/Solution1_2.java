/**
 * @author YonghShan
 * @date 9/4/21 - 17:23
 */
public class Solution1_2 {
    // 完全背包 + 第二版「状态转移方程」
    // 和[0279]一模一样，只是本题初始化从「不考虑任何硬币」开始
    /* Runtime: 23ms (faster than 42.34%)   O(n*amount)
       Memory: 39.4MB (less than 31.67%)     O(n*amount)
     */
    int INF = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] f = new int[n+1][amount+1];

        // 初始化（没有任何硬币的情况）：只有 f[0][0] = 0；其余情况均为无效值。
        // 这是由「状态定义」决定的，当不考虑任何硬币的时候，只能凑出总和为 0 的方案，所使用的硬币数量为 0
        for (int c = 1; c <= amount; c++) f[0][c] = INF;

        // 从「第一枚硬币」开始讨论
        for (int i = 1; i <= n; i++) {
            int t = coins[i-1];
            for (int c = 0; c <= amount; c++) {
                // 「不选」：
                int ns = f[i-1][c];
                // 「选」：因为引入了无效值，所以也要增加f[i][c-t]的无效值判断
                int s = c >= t && f[i][c-t] != INF ? f[i][c-t]+1: INF;
                f[i][c] = Math.min(ns, s);
            }
        }

        return f[n][amount] != INF ? f[n][amount] : -1;
    }
}
