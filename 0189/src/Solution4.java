/**
 * @author YonghShan
 * @date 7/23/21 - 12:12
 */
public class Solution4 {
    // Using Reverse
    /* Runtime: 0ms                       O(n)
       Memory: 56MB (less than 82.74%)    O(1)
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            end--;
            start++;
        }
    }
}
