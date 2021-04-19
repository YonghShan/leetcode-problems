/**
 * @author YonghShan
 * @date 3/12/21 - 17:00
 */
public class Solution1Advanced2 {
    // Fast Power Algorithm Recursive: exponentiation by squaring平方求幂
    // 通过引入辅助函数，实现tail recursion
    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        return fastPow(x, N);
    }
}
