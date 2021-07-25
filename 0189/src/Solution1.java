/**
 * @author YonghShan
 * @date 7/22/21 - 15:24
 */
public class Solution1 {
    // Brute Force: TLE
    // 官方的LeetCode也TLE
    public void rotate(int[] nums, int k) {
        if (k == 0) return;
        k %= nums.length;  // in case that k > nums.length

        int len = nums.length;
        int temp;
        for (int i = 0; i < k; i++) {  // O(nk)
            temp = nums[len-1];
            for (int j = len-1; j > 0; j--) nums[j] = nums[j-1];
            nums[0] = temp;
        }
    }
}
