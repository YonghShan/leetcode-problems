/**
 * @author YonghShan
 * @date 7/22/21 - 01:11
 */
public class Solution1 {
    // Two-Pointer
    /* Runtime: 1ms (faster than 100%)    O(n)
       Memory: 39MB (less than 48.44%)    O(1)
     */
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0, j = 0;
        int sum = 0;
        int minLen = nums.length;
        boolean isExists = false;

        for (; j < nums.length; j++) {  // O(n)
            sum += nums[j];
            while (sum >= target) {  // ？不太清楚具体会循环几次
                isExists = true;
                minLen = Math.min(minLen, j-i+1);
                sum -= nums[i++];
            }
        }

        return isExists ? minLen : 0;
    }
}
