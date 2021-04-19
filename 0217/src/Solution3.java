import java.sql.Time;

/**
 * @author YonghShan
 * @date 4/6/21 - 16:24
 */
public class Solution3 {
    // Brute Force: TLE   O(n^2) & O(1)
    // Usually, if an algorithm is O(n^2), it can handle nn up to around 10^4. It gets Time Limit Exceeded when n≥10^5
    public boolean containsDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] == nums[i]) return true;
            }
        }
        return false;
    }
}
