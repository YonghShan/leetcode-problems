import java.util.Arrays;
import java.util.HashMap;

/**
 * @author YonghShan
 * @date 3/21/21 - 20:00
 */
public class Solution1Advanced {
    // 用HashMap来存储nums1[i]在nums2中的对应位置
    // Better Brute Force: 先在nums2中找到nums1[i]，将isFound置为true，在此基础上，遇到的第一个大于nums1[i]的nums2[j]放入res[i]
    /* Runtime: 2ms (faster than 98.56%)  O(nums1.length * nums2.length)  虽然TC不变，但是速度大大地提升
       Memory: 39MB (less than 75.66%)    O(nums1.length)
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);

        HashMap<Integer, Integer> mapping = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            mapping.put(nums2[i], i);
        }

        for (int i = 0; i < nums1.length; i++) {
            for (int j = mapping.get(nums1[i]); j < nums2.length; j++) {
                if (nums2[j] > nums1[i]) {
                    res[i] = nums2[j];
                    break;
                }
            }
        }
        return res;
    }
}
