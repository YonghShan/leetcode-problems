/**
 * @author YonghShan
 * @date 4/24/21 - 21:27
 */
public class Solution3 {
    // Binary Search: Template 3
    // 也可以写成Recursion版本
    /* Runtime: 0ms    O(log_2 n)
       Memory: 40MB (less than 5.40%)   O(1)
     */
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left+1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= nums[mid+1]) { // mid-1很可能越界，但是mid+1不会（根据java int之间的除法，总是直接约去小数部分）
                left = mid; // nums[mid]有可能是等于nums[mid+1]的
            } else {
                right = mid; // mid仍有可能为target
            }
        }

        return nums[left] > nums[right] ? left : right; // for any nums with the size more than 2, the answer is right. But we should also consider the situation that the size of nums is 2.
    }
}
