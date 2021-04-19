/**
 * @author YonghShan
 * @date 1/28/21 - 01:53
 */

class Solution1 {
    /* Runtime: 110ms
       Memory: 40M
     */
    public int removeDuplicates(int[] nums) {
        int len = nums.length;

        for (int i = 1; i < len; i++) {
            if (nums[i] == nums[i-1]) {
                for (int j = i; j<len-1; j++) {
                    nums[j] = nums[j+1];
                }
                len--;
                i--;
            }
        }
        return len;
    }
}
