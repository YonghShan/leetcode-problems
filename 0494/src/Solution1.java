import java.util.Arrays;

/**
 * @author YonghShan
 * @date 3/4/21 - 16:31
 */
class Solution1 {
    // Recursion "Bottom-up": fTS(nums.length=n, S) = fTS(newNums.length=n-1, S+nums[n-1]) + fTS(newNums.length=n-1, S-nums[n-1])
    // 思路是由给定的nums和S向下递减
    // 不好加上Memoization
    /* Runtime: 560ms   O(2^n -1) = O(2^n), where n is the length of nums
       Memory: 38.3MB   O(n)
     */
    public int findTargetSumWays(int[] nums, int S) {
        if (nums.length == 1) {
            if (nums[0] == S && nums[0]*(-1) == S) return 2;
            if (nums[0] != S && nums[0]*(-1) != S) {
                return 0;
            } else {
                return 1;
            }
        }

        int[] newNums = (int[]) Arrays.copyOf(nums, nums.length-1);
        return findTargetSumWays(newNums, S-nums[nums.length-1]) + findTargetSumWays(newNums, S+nums[nums.length-1]);
    }
}
