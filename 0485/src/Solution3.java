/**
 * @author YonghShan
 * @date 1/26/21 - 23:37
 */

class Solution3 {
    // 和two-pointer一样，只是阐述方式不同
    // 将0作为隔板，利用0的index计算。需要注意的是要在数组首尾各加一个0
    /* Runtime: 3ms   O(n)
       Memory: 53M    O(1)
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int prev = -1; // prev是数组首加的0
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                max = Math.max(i - prev - 1, max);
                prev = i;
            }
        }
        return Math.max(nums.length - prev - 1, max); // nums.length是数组尾加的0
    }
}