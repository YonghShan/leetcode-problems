/**
 * @author YonghShan
 * @date 3/11/21 - 23:34
 */
public class Solution2Advanced {
    // Memory Efficient DP: Bottom-up
    /* Runtime: 0ms      O(n)
       Memory: 35.7MB    O(1)
     */
    public int fib(int n) {
        int i = 0;
        int j = 1; // 相当于length=2的dp array

        for (int k = 2; k < n+1; k++) {
            int temp = i;
            i = j;
            j += temp;
        }
        return n == 0 ? i : j;
    }
}
