import java.util.Arrays;

/**
 * @author YonghShan
 * @date 7/20/21 - 11:06
 */
public class Solution1 {
    // sort后相邻两两配对
    /* Runtime: 10ms (faster than 96.70%)   O(nlogn)
       Memory: 41.3MB (less than 39.42%)    O(1)
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);

        int sum = 0;
        for (int i = 0; i < nums.length-1; i+=2) {
            sum += nums[i]; // 因为已经sorted，所以nums[i] ≤ nums[i+1]
        }

        return sum;
    }
}
