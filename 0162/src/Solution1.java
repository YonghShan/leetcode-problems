/**
 * @author YonghShan
 * @date 4/20/21 - 11:40
 */
public class Solution1 {
    // Binary Search with two clever tricks: see the notes: Template 2
    // 也可以写成Recursion版本
    /* Runtime: 0ms    O(log_2 n)
       Memory: 38.8MB (less than 18.93%)   O(1)
     */
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid+1]) { // mid-1很可能越界，但是mid+1不会（根据java int之间的除法，总是直接约去小数部分）
                left = mid + 1; // 此时，mid处于ascending order，且已知mid不会是target，所以left = mid + 1，即不再考虑mid
            } else {
                right = mid; // mid仍有可能为target
            }
        }

        return left;
    }
}
