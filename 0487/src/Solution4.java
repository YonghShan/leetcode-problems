/**
 * @author YonghShan
 * @date 2/1/21 - 17:13
 */

class Solution4 {
    // Brute Force
    /* Runtime: 2548ms (high likely to be TLE) O(N^2)
       Memory: 40M  O(1)
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int longestSequence = 0;
        for (int left = 0; left < nums.length; left++) {
            int numZeroes = 0;

            // check every consecutive sequence
            for (int right = left; right < nums.length; right++) {
                // count how many 0's
                if (nums[right] == 0) {
                    numZeroes += 1;
                }
                // # update answer if it's valid
                if (numZeroes <= 1) {
                    longestSequence = Math.max(longestSequence, right - left + 1);
                }
            }
        }
        return longestSequence;
    }
}
