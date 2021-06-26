/**
 * @author YonghShan
 * @date 6/25/21 - 18:31
 */
public class Solution1 {
    /* Runtime: 0ms                        O(n)
       Memory: 37MB (less than 33.41%)     O(1)
     */
    public int dominantIndex(int[] nums) {
        if (nums.length == 1) return 0;

        int maxIdx = 0;
        int secondMaxIdx = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[maxIdx]) {
                secondMaxIdx = maxIdx;
                maxIdx = i;
            } else {
                if (nums[i] > nums[secondMaxIdx]) secondMaxIdx = i;
            }
        }

        return nums[maxIdx] >= 2 * nums[secondMaxIdx] ? maxIdx : -1;
    }
}
