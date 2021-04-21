/**
 * @author YonghShan
 * @date 4/20/21 - 16:37
 */
public class Solution1Advanced {
    // 根据[0033]写的改进版
    /* Runtime: 0ms (faster than 100.00%)    O(logn)
       Memory: 38MB (less than 89.24%)       O(1)
     */
    public int search(int[] nums, int target) {
        if (nums.length == 1) return nums[0] == target ? 0 : -1;

        // Step 1: find the smallest element in array using Binary Search
        int left = 0;
        int right = nums.length - 1;
        int idx = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid+1]) {
                idx = mid + 1;
                break;
            } else {
                if (nums[mid] < nums[left]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        // System.out.println(idx);

        // Step 2: find the target using Binary Search
        int start = 0;
        int end = 0;
        if (target <= nums[nums.length - 1]) {
            start = idx;
            end = nums.length - 1;
        } else {
            start = 0;
            end = idx - 1;
        }
        while (start <= end) {
            int middle = start + (end - start) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                start = middle + 1;
            } else {
                end = middle -1;
            }
        }

        return -1;
    }
}
