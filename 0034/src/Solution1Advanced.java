/**
 * @author YonghShan
 * @date 4/23/21 - 00:22
 */
public class Solution1Advanced {
    // 官方Solution, more concise: Binary Search Template I to find the leftmost and rightmost target, respectively
    /* Runtime: 0ms                        O(logn)
       Memory: 42.3MB (less than 29.65%)   O(1)
     */
    public int[] searchRange(int[] nums, int target) {
        int leftmost = findBoundry(nums, target, true);
        if (leftmost == -1) return new int[]{-1, -1};
        int rightmost = findBoundry(nums, target, false);
        return new int[]{leftmost, rightmost};
    }

    public int findBoundry(int[] nums, int target, boolean isFirst) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                if (isFirst == true) {
                    if (mid == left || nums[mid-1] != target) return mid;
                    right = mid - 1;
                } else {
                    if (mid == right || nums[mid+1] != target) return mid;
                    left = mid + 1;
                }
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
