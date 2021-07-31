import java.util.Arrays;

/**
 * @author YonghShan
 * @date 7/29/21 - 15:42
 */
public class Solution2 {
    // DP 1D Array
    /* Runtime: 0ms
       Memory: 35.9MB (less than 53.52%)  O(n)
     */
    public int uniquePaths(int m, int n) {
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[j] = f[j-1] + f[j];
            }
        }
        return f[n-1];
    }
}
