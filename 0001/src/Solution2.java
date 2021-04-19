import java.util.HashMap;

/**
 * @author YonghShan
 * @date 4/9/21 - 14:30
 */
public class Solution2 {
    // HashMap<val, index>
    /* Runtime: 0ms      O(n)
       Memory: 39.2MB    O(n)
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) return new int[]{map.get(target-nums[i]), i};
            map.put(nums[i], i);
        }

        return new int[0];
    }
}
