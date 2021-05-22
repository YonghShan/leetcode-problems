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
        Arrays.sort(nums);    // O(NlogN)

        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0];
        while (lo < hi) {      // O(logW)
            int mi = (lo + hi) / 2;
            int count = 0, left = 0;
            for (int right = 0; right < nums.length; ++right) {     // O(N)
                while (nums[right] - nums[left] > mi) left++;
                count += right - left;
            }
            //count = number of pairs with distance <= mi
            if (count >= k) hi = mi;
            else lo = mi + 1;
        }
        return lo;
    }
}
