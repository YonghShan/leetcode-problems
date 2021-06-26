/**
 * @author YonghShan
 * @date 6/25/21 - 17:15
 */
public class Solution2 {
    // 和Solution1相同，只是Solution1中要不断更新rightSum和leftSum两个值，而Solution2只要更新leftSum
    /* Runtime: 1ms (faster than 100%)     O(n)
       Memory: 39.3MB (less than 90.52%)   O(1)
     */
    public int pivotIndex(int[] nums) {
        int leftSum = 0;
        int totalSum = 0;

        for (int num : nums) {    // O(n)
            totalSum += num;
        }

        for (int i = 0; i < nums.length; i++) {     // O(n)
            if (leftSum == totalSum - leftSum - nums[i]) return i;
            leftSum += nums[i];
        }

        return -1;
    }
}
