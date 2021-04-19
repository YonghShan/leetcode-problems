
/**
 * @author YonghShan
 * @date 3/5/21 - 00:15
 */
class Solution4 {
    // DP with 2D array：官方Solution, 和Solution2填法相同，只是用CONSTANT 2001作为dp的参数
    /* Runtime: 12ms    O(l*n): The entire nums array is traversed l times.
                                n refers to the size of nums array. l refers to the range of sum possible.
       Memory: 38.8MB   O(l*n): dp array of size l*n is used.
     */
    public int findTargetSumWays(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1; // array是0-indexed, 以index 1000 (第1001个)的元素作为中点
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    // 为什么不担心sum + nums[i] + 1000或者sum - nums[i] + 1000会超边界？
                    // 因为初始化dp第一行时，是根据nums[0]的值；而题目已经说了nums中元素的和不会超过1000，
                    // 即nums[0]等于1000时，dp[0][0]和dp[0][2000]设为1，但同时nums中没有其他元素了，nums.length=1,根本就不会进入循环
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000]; // nums中元素的和不会超过1000，S>1000则超界
    }
}