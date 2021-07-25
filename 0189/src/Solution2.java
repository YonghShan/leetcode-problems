/**
 * @author YonghShan
 * @date 7/22/21 - 15:54
 */
public class Solution2 {
    // Extra Array
    /* Runtime: 2ms (faster than 38.34%)    O(n)
       Memory: 56.1MB (less than 61.13%)    O(n)
     */
    public void rotate(int[] nums, int k) {
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];  // 这么写是因为无论是(i+k)还是(i+k%nums.length)都有可能越界
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }
    }
}
