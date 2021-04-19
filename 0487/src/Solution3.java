/**
 * @author YonghShan
 * @date 2/1/21 - 16:46
 */

class Solution3 {
    // Two Pointers but fancier
    /* Runtime: 2ms O(N)
       Memory: 40M  O(1)
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int len = nums.length;
        int max1s = 0;
        int count = 0;
        int lastZeroIndex = -1;

        for (int i = 0; i < len; i++) {
            if(nums[i] == 0) {
                count = i - lastZeroIndex;
                lastZeroIndex = i;
            } else {
                count++;
            }

            max1s = count > max1s ? count : max1s;
        }

        return max1s;
    }
}