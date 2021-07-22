/**
 * @author YonghShan
 * @date 2/1/21 - 17:16
 */

class Solution5 {
    // Two Pointers but less fancy：和Solution 4一样，是依据numZeroes的值来确定范围的
    /* Runtime: 6ms O(N)
       Memory: 53M  O(1)
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int longestSequence = 0;
        int left = 0;
        int right = 0;
        int numZeroes = 0;

        // while our window is in bounds
        while (right < nums.length) {

            // add the right most element into our window
            if (nums[right] == 0) {
                numZeroes++;
            }

            // if our window is invalid, contract our window
            while (numZeroes == 2) {
                if (nums[left] == 0) {
                    numZeroes--;
                }
                left++;
            }

            // update our longest sequence answer
            longestSequence = Math.max(longestSequence, right - left + 1);

            // expand our window
            right++;
        }
        return longestSequence;
    }
}