/**
 * @author YonghShan
 * @date 3/12/21 - 14:14
 */
public class Solution2 {
    // Memory Efficient DP
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
}
