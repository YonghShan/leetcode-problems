/**
 * @author YonghShan
 * @date 3/25/21 - 15:12
 */
public class Solution3 {
    // From Tushar Roy: https://www.youtube.com/watch?v=LPFhl65R7ww 见笔记
    // https://github.com/mission-peace/interview/blob/master/src/com/interview/binarysearch/MedianOfTwoSortedArrayOfDifferentLength.java
    // Take minimum size of two array. Possible number of partitions are from 0 to m in m size array.
    // Try every cut in binary search way. When you cut first array at i then you cut second array at (m + n + 1)/2 - i
    // Now try to find the i where a[i-1] <= b[j] and b[j-1] <= a[i]. So this i is partition around which lies the median.
    /* Runtime: 2ms     O(log(min(x,y)), where x = nums1Len, y = nums2Len
       Memory:  40.3MB  O(1)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int nums1Len = nums1.length;
        int nums2Len = nums2.length;
        if (nums1Len > nums2Len) return findMedianSortedArrays(nums2, nums1);  // 保证第一个参数is shorter, 这样下面start/aMinCount和end/aMaxCount好取值
        int leftHalfLen = (nums1Len + nums2Len + 1) / 2;  // 这样赋值不用考虑nums1Len + nums2Len为odd还是even

        int start = 0;  // i.e. aMinCount：merge后的数组的左半部分中来自nums1的最小元素个数
        int end = nums1Len;  // i.e. aMaxcount：merge后的数组的左半部分中来自nums1的最大元素个数

        while (start <= end) {
            int partitionX = (start + end) / 2; // Binary Search   i.e. aCount：每次都通过binary search来假设merge后的数组的左半部分中来自nums1的元素个数
            int partitionY = leftHalfLen - partitionX;    // i.e. bCount：剩下的都来自nums2

            // if partitionX is 0 it means nothing is there on left side. Use -INF for maxLeftX
            // if partitionX is length of input then there is nothing on right side. Use +INF for minRightX
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX-1];    // i.e. x
            int minRightX = (partitionX == nums1Len) ? Integer.MAX_VALUE : nums1[partitionX];     // i.e. x'
            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY-1];    // i.e. y
            int minRightY = (partitionY == nums2Len) ? Integer.MAX_VALUE : nums2[partitionY];     // i.e. y'

            if (maxLeftX > minRightY) {
                end = partitionX - 1;
            } else if (maxLeftY > minRightX) {
                start = partitionX + 1;
            } else {
                if ((nums1Len + nums2Len) % 2 == 0) {
                    return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                } else {
                    return (double) Math.max(maxLeftX, maxLeftY);
                }
            }
        }

        return -1;
    }
}
