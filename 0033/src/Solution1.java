/**
 * @author YonghShan
 * @date 4/18/21 - 17:23
 */
public class Solution1 {
    // 先Binary Search找出smallest element，将数组分为两个部分；在确定target位于哪半边后，在该半边再使用Binary Search找到target
    /* Runtime: 0ms (faster than 100.00%)    O(logn)
       Memory: 39.2MB (less than 5.64%)      O(1)
     */
    public int search(int[] nums, int target) {
        if (nums.length == 1) return nums[0] == target ? 0 : -1;

        // Step 1: find the smallest element in array using Binary Search
        int left = 0;
        int right = nums.length - 1;
        int idx = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == nums.length - 1) break; // 此时array是顺序的，即rotated at 0
            if (nums[mid] > nums[mid+1]) {
                idx = mid + 1;
                break;
            } else {
                if (nums[mid] < nums[left]) {
                    right = mid - 1;
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
