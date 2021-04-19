import java.util.HashMap;

/**
 * @author YonghShan
 * @date 3/12/21 - 16:18
 */
public class Solution1Advanced {
    // Fast Power Algorithm Recursive: exponentiation by squaring平方求幂
    /* Runtime: 0ms     O(log n) 不停开根号
       Memory: 38.4MB   O(log n)
     */
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        long N = n;
        if (N < 0) {
            N = -N;
            x = 1/x;
        }
        double p = myPow(x, (int) (N/2));
        double res = (N % 2 == 0) ? p*p : p*p*x;
        return res;
    }
}
