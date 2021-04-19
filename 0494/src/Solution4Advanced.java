import java.util.Arrays;

/**
 * @author YonghShan
 * @date 3/5/21 - 22:38
 */
class Solution4Advanced {
    // DP with 1D array: Solution4 advanced: 没有办法in-place, 只能重复利用之前的size=n的1D array更新新的size=n的1D array后，丢弃之前的array
    /* Runtime: 8ms     O(l*n): The entire nums array is traversed 2001(constant no.: l) times.
                                n refers to the size of nums array. l refers to the range of sum possible.
       Memory: 38.5MB   O(n): dp array of size n is used.
     */
    public int findTargetSumWays(int[] nums, int S) {
        int[] dp = new int[2001];
        dp[nums[0] + 1000] = 1;
        dp[-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2001];
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[sum + 1000] > 0) {
                    next[sum + nums[i] + 1000] += dp[sum + 1000];
                    next[sum - nums[i] + 1000] += dp[sum + 1000];
                }
            }
            dp = next;
        }
        return S > 1000 ? 0 : dp[S + 1000];
    }
}