/**
 * @author YonghShan
 * @date 1/30/21 - 17:20
 */

class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0;

        for (int j = 0; j<nums.length; j++) {
            if (nums[j] != 0) {
                nums[i] = nums[j];
                // Without this, we just simply move all the non-zero elements to left.
                // We should also set the last few elements to 0.
                // Attention! We cannot just simply set nums[j] to 0 because we might cover the new nums[i] when i==j.
                if (i != j) nums[j] = 0;
                i++;
            }
        }
    }
}