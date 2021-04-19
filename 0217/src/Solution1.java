import java.util.HashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 4/6/21 - 16:17
 */
class Solution1 {
    /* Runtime: 6ms O(n)
       Memory: 45MB O(n)
     */
    // For certain test cases with not very large n, the runtime of this method can be slower than Approach #4.
    // The reason is hash table has some overhead in maintaining its property.
    // One should keep in mind that real world performance can be different from what the Big-O notation says.
    // The Big-O notation only tells us that for sufficiently large input, one will be faster than the other.
    // Therefore, when n is not sufficiently large, an O(n) algorithm can be slower than an O(nlogn) algorithm.
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }
}
