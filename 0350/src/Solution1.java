import java.util.*;

/**
 * @author YonghShan
 * @date 4/9/21 - 23:29
 */
public class Solution1 {
    // One HashMap<val, count> for nums1, and then iterate nums2 adding the elements into the result only when count is not equal to 0
    /* Runtime: 2ms (faster than 93.98%)     O(n+m), where n = nums1.length, m = nums2.length
       Memory: 39.3MB (less than 35.21%)     O(min(m,n))  We use hash map to store numbers (and their counts) from the smaller array.
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersection(nums2, nums1); // 避免代码重复！！！！
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0)+1);
        }

        int k = 0;
        for (int i : nums2) {
            if (map.getOrDefault(i, 0) > 0) {
                nums1[k++] = i;
                map.put(i, map.get(i)-1);
            }
        }

        return Arrays.copyOf(nums1, k);
    }
}
