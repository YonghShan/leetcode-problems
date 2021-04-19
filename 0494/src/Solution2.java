import java.util.Arrays;

/**
 * @author YonghShan
 * @date 3/5/21 - 16:59
 */
class Solution2 {
    // 最初自己写的dp（因为赋初值没有考虑nums[0]=-nums[0]=0的情况，导致额外考虑多种情况）不用看了！！！浪费时间
    public int findTargetSumWays(int[] nums, int S) {
        if (nums.length == 1) return (nums[0]==S || -nums[0]==S) ? 1 : 0;

        int sum = 0;
        for (int i = 1; i < nums.length; i++) sum += nums[i];
        if (sum == 0 && nums[0] != 0) return (nums[0]==S) ? (int) Math.pow(2, nums.length - 1) : 0; // 第一个数不为0，后n-1个数全为0
        if (sum == nums[nums.length-1] && sum != 0 && nums[nums.length-1] == 0) return (nums[nums.length-1]==S) ? (int) Math.pow(2, nums.length - 1) : 0; // 前n-1个数为0，最后一个数不为0:
        int[][] dp = new int[nums.length][2*sum+1];

        // 为dp的最下面一行赋初值：
        if (nums[0] <= S+sum && nums[0] >= S-sum) dp[0][sum-(S-nums[0])] = 1;
        if (-nums[0] <= S+sum && -nums[0] >= S-sum) dp[0][sum-(S+nums[0])] = 1;
        // 依据最小面一行的初值对上面的内容进行更新：
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < 2*sum+1; j++) {
                if (j-nums[i] < 0 || j+nums[i] > 2*sum) continue;
                if (dp[i-1][j-nums[i]] != 0 || dp[i-1][j+nums[i]] != 0) dp[i][j] = dp[i-1][j-nums[i]] + dp[i-1][j+nums[i]];
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        return (nums[0]==0) ? dp[nums.length-1][sum]*2 : dp[nums.length-1][sum]; // 开头nums[0]为0的任意数组，结果翻倍
    }
}
