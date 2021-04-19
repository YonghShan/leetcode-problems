/**
 * @author YonghShan
 * @date 1/28/21 - 01:18
 */

class Solution3 {
    // Two pointers when the elements to remove are rare
    // To avoid unnecessary copy/move operations required in Solution 2
    /* Runtime: 0ms
       Memory: 37M (for loop) / 39M (while loop)
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int len = nums.length;

//        for (i = 0; i < len; i++) {
//            if (nums[i] == val) {
//                nums[i] = nums[len-1];
//                i--;
//                len--; // Only need this operation when chose for loop
//            }
//        }

        while (i < len) {
            if (nums[i] == val) {
                nums[i] = nums[len - 1];
                // reduce array size by one
                len--;
            } else {
                i++;
            }
        }

        return len;
    }
}
