/**
 * @author YonghShan
 * @date 2/1/21 - 15:42
 */

class Solution2 {
    // Expand Around Center
    /* Runtime: 3ms    O(N^2)
       Memory: 40M     O(1)
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxLen = 0;
        int iLen = 0;
        boolean all1s = true;
        boolean all0s = true;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                all1s = false;
                iLen = expandAroundCenter(nums, i);
            } else {
                all0s = false;
            }
            if (iLen > maxLen) maxLen = iLen;
        }

        if (all1s) return nums.length; // All the value of elements in the Input Array are 1
        if (all0s) return 1; // All the value of elements in the Input Array are 0

        return maxLen;
    }

    public int expandAroundCenter(int[] nums, int index) {
        int left = index - 1;
        int right = index + 1;

        while (left >= 0 && nums[left] == 1) left--;
        while (right < nums.length && nums[right] == 1) right++;
        return right - left - 1;
    }
}
