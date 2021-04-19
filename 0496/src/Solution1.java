import java.util.Arrays;

/**
 * @author YonghShan
 * @date 3/21/21 - 19:34
 */
public class Solution1 {
    // Brute Force: 先在nums2中找到nums1[i]，将isFound置为true，在此基础上，遇到的第一个大于nums1[i]的nums2[j]放入res[i]
    /* Runtime: 8ms (faster than 15.57%)  O(nums1.length * nums2.length)
       Memory: 38.9MB (less than 93.36%)  O(nums1.length)
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);

        for (int i = 0; i < nums1.length; i++) {
            boolean isFound = false;
            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == nums1[i]) isFound = true; // 在nums2中找到了nums1[i]
                if (isFound == true && nums2[j] > nums1[i]) {
                    res[i] = nums2[j];
                    break;
                }
            }
        }
        return res;
    }
}
