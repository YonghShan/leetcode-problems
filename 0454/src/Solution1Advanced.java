import java.util.HashMap;

/**
 * @author YonghShan
 * @date 4/14/21 - 00:28
 */
public class Solution1Advanced {
    // One HashMap
    /* Runtime: 103ms (faster than 17.81%)    O(n^2)
       Memory: 38.9MB (less than 98.63%)      O(l), where l is the size of map1
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        for (int i : nums1) {
            for (int j : nums2) {
                map1.put(i+j, map1.getOrDefault(i+j, 0) + 1);
            }
        }

        int result = 0;
        for (int i : nums3) {
            for (int j : nums4) {
                result += map1.getOrDefault(-(i+j),0);
            }
        }

        return result;
    }
}
