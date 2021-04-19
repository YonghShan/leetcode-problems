import java.util.Set;
import java.util.TreeSet;

/**
 * @author YonghShan
 * @date 4/6/21 - 17:09
 */
public class Solution3 {
    // Sliding Window with BST, which the operations including Search, Insert, Remove all takes O(logk) => O(n * logk) in total
    // 如果用Queue的话，Insert和Remove是O(1), 但是Search是O(k)
    /* Runtime: O(nlogk)
       Memory: O(k)
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new TreeSet<>();   // In Java, you may use a TreeSet or a TreeMap as a self-balancing BST
        for (int i = 0; i < nums.length; i++) {    // O(n)
            if (set.contains(nums[i])) return true;      // O(logk)
            set.add(nums[i]);                            // O(logk)
            if (set.size() > k) set.remove(nums[i-k]);   // O(logk)
        }
        return false;
    }
}
