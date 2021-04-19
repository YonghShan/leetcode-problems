/**
 * @author YonghShan
 * @date 4/17/21 - 23:00
 */
public class Solution2 {
    // Recursion
    /* Runtime: 0ms     O(logn): T(n) = T(n/2) + Theta(n^0)
       Memory: 52.1MB   O(logn)
     */
    public int search(int[] nums, int target) {
        return binarySearchHelper(nums, 0, nums.length-1, target);
    }

    public int binarySearchHelper(int[] nums, int left, int right, int target) {
        if (left > right) return -1;

        int mid = left + (right - left) / 2;
        if (nums[mid] > target) return binarySearchHelper(nums, left, mid-1, target);
        if (nums[mid] < target) return binarySearchHelper(nums, mid+1, right, target);
        return mid;
    }
}
