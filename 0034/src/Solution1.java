/**
 * @author YonghShan
 * @date 4/22/21 - 23:41
 */
public class Solution1 {
    // Binary Search to find the leftmost and rightmost target, respectively
    /* Runtime: 0ms                        O(logn)
       Memory: 42.3MB (less than 29.65%)   O(1)
     */
    public int[] searchRange(int[] nums, int target) {
        int left = findLeftmost(nums, target);
        if (left == -1) return new int[]{-1, -1};
        int right = findRightmost(nums, target);
        return new int[]{left, right};

    }

    public int findLeftmost(int[] nums, int target) {
        int leftmost = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                leftmost = mid;
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return leftmost;
    }

    public int findRightmost(int[] nums, int target) {
        int rightmost = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                rightmost = mid;
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return rightmost;
    }
}
