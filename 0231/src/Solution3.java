/**
 * @author YonghShan
 * @date 7/2/21 - 23:39
 */
public class Solution3 {
    /* Runtime: 1ms (faster than 99.92%)      O(1)
       Memory: 36MB (less than 69.14%)      O(1)
     */
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        long x = (long) n;
        return (x & (x - 1)) == 0;  // 二进制中只有一位是1，且被set to 0
    }
}
