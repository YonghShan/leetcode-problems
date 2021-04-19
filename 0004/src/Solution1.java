import java.util.Arrays;

/**
 * @author YonghShan
 * @date 3/24/21 - 21:08
 */
public class Solution1 {
    // Create a new Merge array
    /* Runtime: 3ms (faster than 43.93%)   O(nlogn), where n is the sum of both lengths of nums1 and nums2
       Memory: 39.9MB (less than 86.03%)   O(n) for new array merge with length n
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //int[] merge = (int[]) Arrays.copyOf(nums1, nums1.length + nums2.length); // 没法直接将merge设为double[]，因为nums1无法从int[]转为double[]   O(nums1.length+nums2.length)
        // Arrays.copyOf() 要比 System.arraycopy() 慢很多，因为前者O(nums1.length+nums2.length),而后者O(nums1.length or nums2.length)
        int[] merge = new int[nums1.length + nums2.length];
        if (nums1.length == 0 || nums2.length == 0) {
            merge = nums1.length == 0 ? nums2 : nums1;
        } else {
            System.arraycopy(nums1, 0, merge, 0, nums1.length);    // 将nums1拷入merge  O(nums1.length)
            System.arraycopy(nums2, 0, merge, nums1.length, nums2.length); // 将nums2拷入merge  O(nums2.length
            Arrays.sort(merge); // O(nlogn), where n is the sum of both lengths of nums1 and nums2
        }

        int len = merge.length;
        int mid = len / 2;
        double median = (len % 2 == 0) ? ((double) merge[mid-1] + (double) merge[mid]) / 2 : (double) merge[mid];
        return median;
    }
}
