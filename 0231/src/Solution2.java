/**
 * @author YonghShan
 * @date 7/2/21 - 23:39
 */
public class Solution2 {
    /* Runtime: 1ms (faster than 99.92%)      O(1)
       Memory: 35.8MB (less than 81.98%)      O(1)
     */
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        long x = (long) n;
        return (x & (-x)) == x;  // 二进制只有一位是1
    }
}
