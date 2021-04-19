import java.util.HashSet;

/**
 * @author YonghShan
 * @date 4/8/21 - 16:11
 */
public class Solution1 {
    // Two HashSets
    /* Runtime: 2ms (faster than 94.34%)     O(n+m), where n = nums1.length, m = nums2.length
       Memory: 39.4MB (less than 15.86%)     O(n+m) in the worst case when all elements in the arrays are different.
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int i : nums1) set.add(i);
        for (int i : nums2) {
            if (set.contains(i)) set2.add(i); // 第二个也用HashSet是因为nums2中也有可能元素重复
        }

        int[] res = new int[set2.size()];
        int j = 0;
        for (Integer i : set2) {
            res[j++] = i;
        }

        return res;
    }
}
