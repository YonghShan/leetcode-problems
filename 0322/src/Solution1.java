/**
 * @author YonghShan
 * @date 9/3/21 - 22:57
 */
public class Solution1 {
    // 和[0279]一模一样，只是本题初始化从「不考虑任何硬币」开始
    /* Runtime: 734ms (faster than 5.09%)   O(n*amount^2)
       Memory: 54.1MB (less than 5.02%)     O(n*amount)
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
                f[i][c] = f[i-1][c];
                // 「选」：
                for (int k = 1; k * t <= c; k++)
                    if (f[i-1][c-k*t] != INF) // 要想省略这个判断，可以将INF设为比INT_MAX小的较大数 (e.g. 0x3f3f3f3f)
                        f[i][c] = Math.min(f[i][c], f[i-1][c-k*t]+k);
            }
        }

        return f[n][amount] != INF ? f[n][amount] : -1;
    }
}
