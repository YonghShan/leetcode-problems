import java.util.HashSet;

/**
 * @author YonghShan
 * @date 4/8/21 - 15:29
 */
public class Solution3 {
    // 2 * (a + b + c) - (a + a + b + b + c) = c
    /* Runtime: 6ms (faster than 46.67%)     O(n)
       Memory: 38.8MB (less than 96.24%)     O(n)
     */
    public int singleNumber(int[] nums) {
        int sumOfset = 0;
        int sumOfnums = 0;

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
                sumOfset += nums[i];
            }
            sumOfnums += nums[i];
        }

        return 2 * sumOfset - sumOfnums;
    }
}
