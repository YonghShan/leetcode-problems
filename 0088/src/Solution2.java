/**
 * @author YonghShan
 * @date 1/27/21 - 16:51
 */

class Solution2 {
    // Three pointers and start from the beginning: two read pointers (p1 & p2), one write pointer (p)
    /* Runtime = 0ms
       Memory = 39.5M
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Step1: make a copy of nums1 with only the length of m
        int[] nums1_copy = new int[m];

        for (int i = 0; i<m; i++) {
            nums1_copy[i] = nums1[i];
        }

        // Step2: Set two pointers, each for nums1_copy and nums2
        int p1 = 0;
        int p2 = 0;

        // Step3: Start from the beginning and compare the values from nums1_copy and nums2
        for (int p = 0; p<m+n; p++) {
            if (p2 >= n || (p1 < m && nums1_copy[p1] < nums2[p2])) {
                nums1[p] = nums1_copy[p1];
                p1++;
            } else {
                nums1[p] = nums2[p2];
                p2++;
            }
        }
    }
}
