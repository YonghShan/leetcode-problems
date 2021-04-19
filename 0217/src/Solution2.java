import java.util.HashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 4/6/21 - 16:18
 */
class Solution2 {
    /* Runtime: 5ms   O(n)
       Memory: 46.4MB O(n)
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        return nums.length != set.size();
    }
}
