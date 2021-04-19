/**
 * @author YonghShan
 * @date 3/12/21 - 16:38
 */
public class Solution2Advanced {
    // Fast Power Algorithm Iterative: exponentiation by squaring平方求幂
    // O(log n) O(1)
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double current_product = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }
}
