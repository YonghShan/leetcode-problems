/**
 * @author YonghShan
 * @date 3/6/21 - 16:25
 */
public class SolutionFinal {
    // DP: swap the constant 1000 to the sum of the ints in the array.
    // Combination of Solution 3 and Solution 4
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int i : nums) sum += i;
        if (S > sum || S < -sum) return 0;
        int[][] dp = new int[nums.length][sum*2+1];
        dp[0][nums[0]+sum] = 1;
        dp[0][-nums[0]+sum] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = -sum; j <= sum; j++) {
                if (dp[i-1][j+sum] != 0) {
                    int n = nums[i];
                    dp[i][j+sum+n] += dp[i-1][j+sum];
                    dp[i][j+sum-n] += dp[i-1][j+sum];
                }
            }
        }
        return dp[nums.length-1][S+sum];
    }
}
