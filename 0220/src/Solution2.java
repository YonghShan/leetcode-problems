import java.util.TreeSet;

/**
 * @author YonghShan
 * @date 4/6/21 - 22:37
 */
public class Solution2 {
    // BST as Sliding Window
    /* Runtime: 55ms    O(nlog(min(n,k)))
       Memory: 41.8MB   O(min(n,k))
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> bst = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {  // O(n)
            // BST: Insert/Search/Delete: O(log(min(n,k)))
            if (bst.ceiling(nums[i]) != null) { // 找到大于或等于nums[i]的最小值
                long s = bst.ceiling(nums[i]).longValue();
                if (s - (long) nums[i] <= t) return true;
            }
            if (bst.floor(nums[i]) != null) {   // 找到小于或等于nums[i]的最大值
                long g = bst.floor(nums[i]).longValue();
                if ((long) nums[i] - g <= t) return true;
            }
            bst.add(nums[i]);
            if (bst.size() > k) bst.remove(nums[i-k]);
        }

        return false;
    }
}
