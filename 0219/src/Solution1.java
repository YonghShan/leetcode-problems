/**
 * @author YonghShan
 * @date 4/6/21 - 16:49
 */
public class Solution1 {
    /* Runtime: 1634ms   O(kn) k是可以等于n，到时为O(n^2)
       Memory: 41.4MB    O(1)
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {    // O(n)
            for (int j = i + 1; j <= i + k; j++) {  // O(k)
                if (j < nums.length && nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
