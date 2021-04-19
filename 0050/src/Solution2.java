/**
 * @author YonghShan
 * @date 3/12/21 - 16:14
 */
public class Solution2 {
    // Brute Force: Iteration: TLE
    // O(n) O(1)
    public double myPow(double x, int n) {  // -2^31 <= n <= 2^31-1
        long N = n; // n = Integer.MIN_VALUE = -2147483648,when you do -n it changes to 2147483648，所以将n先转化为long，防止下面取负时出错
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        for (long i = 0; i < N; i++)
            ans = ans * x;
        return ans;
    }
}
