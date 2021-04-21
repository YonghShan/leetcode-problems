/**
 * @author YonghShan
 * @date 4/20/21 - 16:31
 */
public class Solution1 {
    // [0033] Solution1Advanced的Step 1
    // Binary Search find the smallest element
    /* Runtime: 0ms                         O(logn)
       Memory: 38.6MB (less than 24.60%)    O(1)
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid+1]) return nums[mid+1];
            if (nums[mid] < nums[left]) {
                right = mid;  // 这种情况，mid还是有可能为smallest element的，所以right赋为mid
            } else {
                left = mid + 1;
            }
        }

        return nums[0];
    }
}
