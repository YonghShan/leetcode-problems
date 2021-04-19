import java.util.Arrays;

/**
 * @author YonghShan
 * @date 3/5/21 - 23:47
 */
public class Solution5Advanced {
    // 官方Recursion with Memoization
    /* Runtime: O(l⋅n): The memo array of size l*n has been filled just once.
                        Here, l refers to the range of sum and n refers to the size of nums array.
       Memory:  O(l⋅n): The depth of recursion tree can go upto n. The memo array contains l⋅n elements.
     */
    int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
        int[][] memo = new int[nums.length][2001];
        for (int[] row: memo)
            Arrays.fill(row, Integer.MIN_VALUE);
        return calculate(nums, 0, 0, S, memo);
    }
    public int calculate(int[] nums, int i, int sum, int S, int[][] memo) {
        if (i == nums.length) {
            if (sum == S)
                return 1;
            else
                return 0;
        } else {
            if (memo[i][sum + 1000] != Integer.MIN_VALUE) {
                return memo[i][sum + 1000];
            }
            int add = calculate(nums, i + 1, sum + nums[i], S, memo);
            int subtract = calculate(nums, i + 1, sum - nums[i], S, memo);
            memo[i][sum + 1000] = add + subtract;
            return memo[i][sum + 1000];
        }
    }
}
