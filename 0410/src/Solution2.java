/**
 * @author YonghShan
 * @date 5/22/21 - 23:22
 */
public class Solution2 {
    // Dynamic Programming
    /* Runtime: 137ms (faster than 9.46%)    O(n^2*m) for DP part 见下
       Memory: 38.5MB (less than 6.24%)      O(n*m) for dp array
     */
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n+1][m+1];    // dp[i][j]中存的值表示：将长度为i的数组nums[0...i]分割成j个subarrays后，得到的minimum largest subarray sum
        int[] sub = new int[n+1];    // sub[i]中存的值表示：nums中前i个元素之和

        // Initialization
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MAX_VALUE;   // 因为对于j>i的情况，dp[i][j] is invalid，要置为INFINITY，所以为方便全置为INFINITY
            }
        }
        dp[0][0] = 0;
        for (int i = 0; i < n; i++) {
            sub[i+1] = sub[i] + nums[i];
        }

        // DP: dp[i][j]由max(dp[k][j-1], nums[k+1]+...+nums[i])决定，而k \in [0, i)，所以更准确地说，dp[i][j] = min[max(dp[k][j-1], nums[k+1]+...+nums[i])]
        //     解释：当长度为i时，从0～k的范围内分割出(j-1)个subarray后，从k+1～i组成第j个subarray
        for (int i = 1; i <= n; i++) {           // takes O(n)
            for (int j = 1; j <= m; j++) {       // takes O(m)
                for (int k = 0; k < i; k++) {    // takes O(n)
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j-1], sub[i] - sub[k]));
                }
            }
        }

        return dp[n][m];
    }
}
