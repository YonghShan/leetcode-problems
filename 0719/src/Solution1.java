/**
 * @author YonghShan
 * @date 5/21/21 - 15:23
 */
public class Solution1 {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);

        // find the min-difference and the max-difference
        int min = 0;
        for (int i = 0; i < nums.length-1; i++) {
            min = Math.min(min, nums[i+1] - nums[i]);
        }

        int left = min;
        int right = nums[length-1] - nums[0];
        while (left < right) {
            int mid = left + (right - left);
            if (checkDiff(nums, mid) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public int checkDiff(int[] nums, int mid) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result += upperBond(nums, i, nums.length-1, nums[i]+mid) - (i+1);
        }
        return result;
    }

    public int upperBond(int[] nums, int src, int end, int target) {
        int left = src;
        int right = end;
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
