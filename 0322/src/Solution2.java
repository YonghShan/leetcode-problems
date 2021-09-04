/**
 * @author YonghShan
 * @date 9/3/21 - 23:49
 */
public class Solution2 {
    int INF = 0x3f3f3f3f;
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] f = new int[amount+1];

        // 初始化（没有任何硬币的情况）：只有 f[0] = 0；其余情况均为无效值。
        // 这是由「状态定义」决定的，当不考虑任何硬币的时候，只能凑出总和为 0 的方案，所使用的硬币数量为 0
        for (int c = 1; c <= amount; c++) f[c] = INF;

        // 从「第一枚硬币」开始讨论
        for (int i = 1; i <= n; i++) {
            int t = coins[i-1];
            for (int c = t; c <= amount; c++) {
                f[c] = Math.min(f[c], f[c-t]+1);
            }
        }

        return f[amount] != INF ? f[amount] : -1;
    }
}
