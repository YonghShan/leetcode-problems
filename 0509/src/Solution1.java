/**
 * @author YonghShan
 * @date 3/11/21 - 23:16
 */
public class Solution1 {
    // Recursion
    /* Runtime: 7ms     O(2^n)
       Memory: 36MB     O(n)
     */
    public int fib(int n) {
        if (n <= 1) return n;
        return fib(n-1)+fib(n-2);
    }
}
