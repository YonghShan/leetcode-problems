/**
 * @author YonghShan
 * @date 9/4/21 - 00:39
 */
public class Solution2 {
    // 完全背包 +「一维空间优化」
    // 和[0322]一样
    /* Runtime: 5ms (faster than 56.48%)  O(n*amount)
       Memory: 38.7MB (less than 50.18%)   O(amount)
     */
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] f = new int[amount+1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            int val = coins[i-1];
            for (int c = val; c <= amount; c++)
                f[c] += f[c-val];
        }
        return f[amount];
    }
}
