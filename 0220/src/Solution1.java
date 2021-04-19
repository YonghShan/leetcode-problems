/**
 * @author YonghShan
 * @date 4/6/21 - 22:36
 */
public class Solution1 {
    // TLE: O(n^2)
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + k; j++) {
                if (j < nums.length && Math.abs((long) nums[i] - (long) nums[j]) < (long) t + 1) return true;
            }
        }
        return false;
    }
}
