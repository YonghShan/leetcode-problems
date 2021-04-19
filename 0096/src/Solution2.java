
/**
 * @author YonghShan
 * @date 3/13/21 - 16:18
 */
public class Solution2 {
    // DP
    /* Runtime: 0ms                       O(n^2)
       Memory: 36MB (less than 29.10%)    O(n)
     */

    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < n+1; i++) { // 从dp[2]开始为dp计算值
            for (int j = 1; j < i+1; j++) { // 每一个n要对应n种情况
                dp[i] += dp[j - 1] * dp[i - j];   // 相对于Solution1，dp[i]即res，dp[j-1]即left，dp[i-j]即right
            }
        }

        return dp[n];
    }
}
