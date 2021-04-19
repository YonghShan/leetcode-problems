import java.util.HashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 4/6/21 - 16:50
 */
public class Solution2 {
    // Sliding Window with HashSet, which the operations including Search, Add, Remove all takes O(1) => O(n * 1) in total
    /* Runtime: 7ms    O(n)
       Memory: 42.1MB  O(k)
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {    // O(n)
            if (set.contains(nums[i])) return true;     // O(1)
            set.add(nums[i]);                           // O(1)
            if (set.size() > k) set.remove(nums[i-k]);  // O(1)
        }
        return false;
    }
}
