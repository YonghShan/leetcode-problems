/**
 * @author YonghShan
 * @date 1/27/21 - 17:21
 */

class Solution3 {
    // Three pointers and start from the end: two read pointers (p1 & p2), one write pointer (p)
    /* Runtime = 0ms
       Memory = 39.5M
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Step1: Set two pointers, each for nums1 and nums2
        int p1 = m-1;
        int p2 = n-1;

        // Step2: Start from the end and compare the values from nums1 and nums2
        for (int p = m+n-1; p>=0; p--) {
            if (p2 < 0) {
                break;
            } else {
                if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                    nums1[p] = nums1[p1];
                    p1--;
                } else {
                    nums1[p] = nums2[p2];
                    p2--;
                }
            }
        }
    }
}

