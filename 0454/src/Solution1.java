import java.util.HashMap;

/**
 * @author YonghShan
 * @date 4/14/21 - 00:26
 */
public class Solution1 {
    // Two HashMap
    /* Runtime: 135ms (faster than 6.59%)    O(n^2)
       Memory: 39.2MB (less than 97.17%)     O(l+m), where l is the size of map1 and m is the size of map2
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        for (int i : nums1) {
            for (int j : nums2) {
                map1.put(i+j, map1.getOrDefault(i+j, 0) + 1);
            }
        }

        HashMap<Integer, Integer> map2 = new HashMap<>();
        for (int i : nums3) {
            for (int j : nums4) {
                map2.put(i+j, map2.getOrDefault(i+j, 0) + 1);
            }
        }

        int result = 0;
        for (Integer i : map1.keySet()) {
            if (map2.containsKey(-i)) result += map1.get(i) * map2.get(-i);
        }

        return result;
    }
}
