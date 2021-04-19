import java.util.Arrays;

/**
 * @author YonghShan
 * @date 3/25/21 - 00:00
 */
public class Solution2 {
    // [0088] Merge Sorted Array
    // Two Pointers (Start From the Beginning)
    /* Runtime: 2ms (faster than 99.85%)   O(n), where n = totalLen
       Memory: 40.6MB (less than 18.97%)   O(n)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLen = nums1.length + nums2.length;
        int[] merge = new int[totalLen];

        if (nums1.length == 0 || nums2.length == 0) {
            merge = nums1.length == 0 ? nums2 : nums1;
        } else {
            int p1 = 0;
            int p2 = 0;
            for (int i = 0; i < totalLen; i++) {     // O(totalLen)
                if (p2 >= nums2.length || (p1 < nums1.length && nums1[p1] <= nums2[p2])) {  // 两个判断条件顺序不能变
                    merge[i] = nums1[p1++];
                } else {
                    merge[i] = nums2[p2++];
                }
            }
        }

        int mid = totalLen / 2;
        double median = (totalLen % 2 == 0) ? ((double) merge[mid-1] + (double) merge[mid]) / 2 : (double) merge[mid];
        return median;
    }
}
