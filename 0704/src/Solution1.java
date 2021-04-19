/**
 * @author YonghShan
 * @date 4/17/21 - 22:55
 */
public class Solution1 {
    // Iteration
    /* Runtime: 0ms     O(logn): T(n) = T(n/2) + Theta(n^0)
       Memory: 51.4MB   O(1)
     */
    public int search(int[] nums, int target) {
        int result = -1;

        int left = 0;
        int right = nums.length-1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                result = mid;
                break;
            }
        }

        return result;
    }
}
