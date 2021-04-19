/**
 * @author YonghShan
 * @date 3/5/21 - 23:45
 */
public class Solution5 {
    // 官方Recursion
    // 由全为0的情况开始递增
    /* Runtime: O(2^n -1) = O(2^n), where n is the length of nums
       Memory:  O(n)
     */
    int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
        calculate(nums, 0, 0, S);
        return count;
    }
    public void calculate(int[] nums, int i, int sum, int S) {
        if (i == nums.length) {
            if (sum == S)
                count++;
        } else {
            calculate(nums, i + 1, sum + nums[i], S);
            calculate(nums, i + 1, sum - nums[i], S);
        }
    }
}
