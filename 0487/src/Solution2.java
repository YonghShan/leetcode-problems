/**
 * @author YonghShan
 * @date 2/1/21 - 15:42
 */

class Solution2 {
    // Expand Around Center：只要遇到0，就以这个0为中向两边找1
    /* Runtime: 3ms    O(N^2)
       Memory: 40M     O(1)
     */
    // 全为0的情况不用特别考虑，但是全为1需要
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxLen = 0;
        int iLen = 0;
        boolean all1s = true;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                all1s = false;
                iLen = expandAroundCenter(nums, i);
            }
            if (iLen > maxLen) maxLen = iLen;
        }

        return all1s ? nums.length : maxLen;
    }

    public int expandAroundCenter(int[] nums, int index) {
        int left = index - 1;
        int right = index + 1;

        while (left >= 0 && nums[left] == 1) left--;
        while (right < nums.length && nums[right] == 1) right++;
        return right - left - 1;
    }
}
