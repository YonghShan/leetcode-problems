import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 4/9/21 - 23:24
 */
public class Solution3 {
    // Two Pointers
    // Facebook Interview Question with the following constrains:
    // 1. O(n) time and O(1) space (the resulting array of intersections is not taken into consideration).
    // 2. The lists are sorted.
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
            return new int[0];

        int len1 = nums1.length;
        int len2 = nums2.length;

        // If not sorted
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        Set<Integer> result = new HashSet<>(); // Avoid duplicates

        for (int idx1 = 0, idx2 = 0; idx1 < len1 && idx2 < len2; ) {
            if (nums1[idx1] == nums2[idx2]) {
                result.add(nums1[idx1]);
                idx1++;
                idx2++;
            } else if (nums1[idx1] < nums2[idx2]) idx1++;
            else
                idx2++;
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}
