/**
 * @author YonghShan
 * @date 4/18/21 - 22:29
 */
public class Solution2 {
    // 直接Binary Search找target 更喜欢这种
    /* Runtime: 0ms (faster than 100.00%)    O(logn)
       Memory: 38.5MB (less than 32.28%)     O(1)
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[left]) { // mid在前半部分
                if (target >= nums[left] && target < nums[mid]) { // 在“mid在前半部分”的前提下，仅仅是target < nums[mid]对应两种情况
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // mid在后半部分
                if (target <= nums[right] && target > nums[mid]) { // 在“mid在后半部分”的前提下，仅仅是target > nums[mid]对应两种情况
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
