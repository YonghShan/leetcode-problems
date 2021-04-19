/**
 * @author YonghShan
 * @date 1/26/21 - 23:31
 */

class Solution2 {
    // One Pass
    /* Runtime: 2ms
       Memory: 53M
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int maxCount = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) {
                // Increment the count of 1's by 1, whenever you encounter a 1
                count += 1;
            } else { //whenever you encounter 0
                // Find the maximum till now
                maxCount = Math.max(maxCount, count);
                // Reset count of 1s to 0
                count = 0;
            }
        }
        return Math.max(maxCount, count);
    }
}

