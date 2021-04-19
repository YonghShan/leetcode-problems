/**
 * @author YonghShan
 * @date 4/17/21 - 23:50
 */
public class Solution4 {
    // 等价于求f(x) = x^2 - S = 0, where S is the target
    // 应用Newton's Method: x_n+1 = x_n - f(x) / f'(x) = x_n - (x^2 - S) / 2x_n = 1/2 (x_n + S / x_n)
    /* Runtime: O(logn)
       Memory: O(1）
     */
    // Solution 1/3/4的Time Complexity都为O(logn): 性能比较(从优到差)：4 > 2 > 1 > 3
    public int mySqrt(int x) {
        if (x < 2) return x;

        double x0 = x;
        double x1 = (x0 + x / x0) / 2.0;
        while (Math.abs(x0 - x1) >= 1) {
            x0 = x1;
            x1 = (x0 + x / x0) / 2.0;
        }

        return (int)x1;
    }
}
