/**
 * @author YonghShan
 * @date 7/2/21 - 23:39
 */
public class Solution1 {
    /* Runtime: 1ms (faster than 99.92%)      O(logn)
       Memory: 36.1MB (less than 53.57%)      O(1)
     */
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        while (n % 2 == 0) n /= 2;
        return n == 1;
    }
}
