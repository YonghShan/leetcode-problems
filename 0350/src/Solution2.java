import java.util.Arrays;

/**
 * @author YonghShan
 * @date 4/10/21 - 00:09
 */
public class Solution2 {
    // 对应[0349]的Solution3
    /* Runtime: O(nlogn+mlogm), where n and m are the lengths of the arrays. We sort two arrays independently, and then do a linear scan.
       Memory: from O(logn+logm) to O(n+m), depending on the implementation of the sorting algorithm.
               For the complexity analysis purposes, we ignore the memory required by inputs and outputs.
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); // O(nlogn)
        Arrays.sort(nums2); // O(mlogm)
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                ++i;
            } else if (nums1[i] > nums2[j]) {
                ++j;
            } else {
                nums1[k++] = nums1[i++];
                ++j;
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }
}

/* 1. What if the given array is already sorted? How would you optimize your algorithm?
        We can use either Approach 2, dropping the sort of course. It will give us linear time and constant memory complexity.

   2. What if nums1's size is small compared to nums2's size? Which algorithm is better?
        Approach 1 is a good choice here as we use a hash map for the smaller array.

   3. What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
        If nums1 fits into the memory, we can use Approach 1 to collect counts for nums1 into a hash map. Then, we can sequentially load and process nums2.
        If neither of the arrays fit into the memory, we can apply some partial processing strategies:
            Split the numeric range into subranges that fits into the memory. Modify Approach 1 to collect counts only within a given subrange, and call the method multiple times (for each subrange).
            Use an external sort for both arrays. Modify Approach 2 to load and process arrays sequentially.
 */