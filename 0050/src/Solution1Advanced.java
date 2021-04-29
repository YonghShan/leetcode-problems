import java.util.HashMap;

/**
 * @author YonghShan
 * @date 3/12/21 - 16:18
 */
public class Solution1Advanced {
    // Binary Search的另类应用
    // Fast Power Algorithm Recursive: exponentiation by squaring平方求幂
    /* Runtime: 0ms     O(log n) 不停开根号
       Memory: 38.4MB   O(log n)
     */
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        long N = n;   // -2^31 <= n <= 2^31-1 ==> 当n=-2^31时，n=-n会超限，所以要先将n赋给long N
        if (N < 0) {
            N = -N;
            x = 1/x;
        }
        double p = myPow(x, (int) (N/2));
        double res = (N % 2 == 0) ? p*p : p*p*x;
        return res;
    }
}
