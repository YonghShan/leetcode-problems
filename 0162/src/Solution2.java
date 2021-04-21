/**
 * @author YonghShan
 * @date 4/20/21 - 16:12
 */
public class Solution2 {
    // Iteration: 1 <= nums.length <= 1000
    /* Runtime: O(n)
       Memory: O(1)
     */
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }
        return nums.length - 1;
    }
}
