import java.util.Arrays;

/**
 * @author YonghShan
 * @date 5/21/21 - 15:23
 */
public class Solution1 {
    // geekforgeeks (https://www.geeksforgeeks.org/k-th-smallest-absolute-difference-two-elements-array/) 比Leetcode Approach #2更好理解
    /* Runtime: 24ms (faster than 21.02%)      O(n*logn + n*log*logn)
       Memory: 39.2MB (less than 34.66%)       O(1)
     */
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);   // O(nlogn)

        // find the min-difference and the max-difference
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length-1; i++) {         // O(n)
            min = Math.min(min, nums[i+1] - nums[i]);
        }

        // find the k-th least difference       O(n*logn*logn)
        int left = min;
        int right = nums[nums.length-1] - nums[0];
        while (left < right) {
            int mid = left + (right - left) / 2;
            int num = checkDiff(nums, mid);
            if (num < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    // 返回difference <= mid的pair的数量        O(n*logn)
    public int checkDiff(int[] nums, int mid) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result += upperBond(nums, i, nums.length-1, nums[i]+mid) - (i+1);    // upperBond返回的是nums中第一个大于(nums[i]+mid)的值的index，
        }                                                                                    // 减去(i+1)，即得到相对于nums[i], difference <= mid的pair的数量
        return result;
    }

    // 在[src, end]的范围内，找到第一个大于target的值的index并返回        O(logn)
    public int upperBond(int[] nums, int src, int end, int target) {
        int left = src;
        int right = end;
        if (target >= nums[end]) return end+1;    // 关键！！！当target(i.e. nums[i]+mid) >= nums[end]时，返回end+1
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
