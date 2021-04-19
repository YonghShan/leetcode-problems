import java.util.Arrays;

/**
 * @author YonghShan
 * @date 4/6/21 - 16:27
 */
public class Solution4 {
    // sort: O(nlogn) & O(1)
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }
}
