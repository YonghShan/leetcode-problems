import java.util.HashMap;

/**
 * @author YonghShan
 * @date 4/8/21 - 11:26
 */
public class Solution2 {
    // HashMap: <val, times>
    /* Runtime: 9ms (faster than 32.43%)     O(n)
       Memory: 39.1MB (less than 70.57%)     O(n/2)
     */
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0)+1);
        }

        for (int i : nums) {
            if (map.get(i) == 1) return i;
        }

        return 0;
    }
}
