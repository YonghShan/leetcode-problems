import java.util.Arrays;

/**
 * @author YonghShan
 * @date 5/21/21 - 23:37
 */
public class Solution2 {
    // 对Solution1中如何找到difference ≤ mid的pair的数量部分采用了更简单的做法
    /* Runtime: O(NlogW + NlogN), where N is the length of nums, and W is equal to nums[nums.length - 1] - nums[0]
       Memory: O(1)
     */
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);

        int left = 0;
        int right = nums[nums.length - 1] - nums[0];
        while (left < right) {
            int mid = left + (right - left) / 2;
            int num = 0;
            int src = 0;
            for (int end = 0; end < nums.length; end++) {
                while (nums[end] - nums[src] > mid) src++;
                num += end - src;
            }
            if (num < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
