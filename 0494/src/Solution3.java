import java.util.Arrays;

/**
 * @author YonghShan
 * @date 3/4/21 - 20:55
 */
class Solution3 {
    // DP：最关键的一步：防止nums[0]=-nums[0]=0的情况,就可以少讨论很多特殊情况
    /* Runtime: 89ms
       Memory: 38.7MB
     */
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int i = 1; i < nums.length; i++) sum += nums[i];  // 这里i从0或1开始都行，从1开始的分析看笔记旧.填法二
        int[][] dp = new int[nums.length][2*sum+1];

        // 为dp的最下面一行赋初值：
        if (nums[0] <= S+sum && nums[0] >= S-sum) dp[0][sum-(S-nums[0])] = 1;
        if (-nums[0] <= S+sum && -nums[0] >= S-sum) dp[0][sum-(S+nums[0])] += 1; // 最关键的一步：防止nums[0]=-nums[0]=0的情况

        // 依据最小面一行的初值对上面的内容进行更新：
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < 2*sum+1; j++) {
                // 本填法是将S放在最中间，这里直接continue的处理绝对不会影响S结果的准确性，但是dp中的其他数的结果可能会有影响
                if (j-nums[i] < 0 || j+nums[i] > 2*sum) continue;
                if (dp[i-1][j-nums[i]] != 0 || dp[i-1][j+nums[i]] != 0) dp[i][j] = dp[i-1][j-nums[i]] + dp[i-1][j+nums[i]];
            }
        }
        // 输出dp的内容：
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[nums.length-1][sum];
    }
}