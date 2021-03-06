/**
 * @author YonghShan
 * @date 9/26/21 - 18:56
 */
public class Solution1 {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right- left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}
