/**
 * @author YonghShan
 * @date 3/13/21 - 16:37
 */
public class Solution3 {
    // Catalan Number:
    // C(0) = 1 and C(n+1) = 2(2n+1)/(n+2) * C(n)
    /* Runtime: O(n)
       Memory:  O(1)
     */
    public int numTrees(int n) {
        // Note: we should use long here instead of int, otherwise overflow
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }
}
