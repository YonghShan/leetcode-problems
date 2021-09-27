/**
 * @author YonghShan
 * @date 9/26/21 - 23:18
 */
public class Solution1 {
    public int subarraySum(int[] nums, int k) {
        int cnt = 0;

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) cnt++;
            }
        }

        return cnt;
    }
}
