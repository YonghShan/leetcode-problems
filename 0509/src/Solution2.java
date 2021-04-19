/**
 * @author YonghShan
 * @date 3/11/21 - 23:28
 */
public class Solution2 {
    // DP: Bottom-up
    /* Runtime: 0ms       O(n)
       Memory: 35.7MB     O(n)
     */
    public int fib(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        if (n > 0) dp[1] = 1;
        for (int i = 2; i < n+1; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
