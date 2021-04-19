import java.util.Arrays;

/**
 * @author YonghShan
 * @date 1/27/21 - 16:36
 */

class Solution1 {
    //Just combine two arrays and then use Arrays.sort() to reorder the combining array
    /* Runtime = 1ms
       Memory = 39.3M
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[i+m] = nums2[i];
        }
        Arrays.sort(nums1);
    }
}
