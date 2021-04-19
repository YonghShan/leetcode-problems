/**
 * @author YonghShan
 * @date 4/17/21 - 23:43
 */
public class Solution3 {
    // Recursion + Bit shifts: sqrt(x) = 2 × sqrt(x/4)
    // Since x << y that means x × 2^y
    //       x >> y that means x / 2^y
    // Then sqrt(x) = sqrt(x>>2) << 1
    /* Runtime: O(logn): T(n) = T(n/2) + Theta(n^0)
       Memory: O(logn) for recursion stack
     */
    public int mySqrt(int x) {
        if (x < 2) return x;   // Base case for recursion: sqrt(x) = x for x < 2

        int left = mySqrt(x >> 2) << 1;
        int right = left + 1;
        return (long)right * right > x ? left : right;
    }
}
