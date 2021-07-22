/**
 * @author YonghShan
 * @date 1/27/21 - 23:57
 */

class Solution1 {
    // Compare the elements of array with the reference value from the end of array
    // If equal, all the elements behind this elements left shift by one
    // 采取"若相等，则其后元素依次左移"的处理方式，只能选择从后往前遍历
    /* Runtime: 0ms
       Memory: 38M
     */
    public int removeElement(int[] nums, int val) {
        int len = nums.length;

        for (int i = len-1; i>=0; i--) {
            if (nums[i] == val) {
                for (int j = i+1; j<len; j++) {
                    nums[j-1] =nums[j];
                }
                len--;
            }
        }
        return len;
    }
}
