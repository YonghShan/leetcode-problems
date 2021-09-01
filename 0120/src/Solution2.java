import java.util.List;

/**
 * @author YonghShan
 * @date 7/31/21 - 01:49
 */
public class Solution2 {
    // j 倒序 改用一维数组
    /* Runtime: 1ms (faster than 96.07%)
       Memory: 39MB (less than 59.53%)      O(n)
     */
    public int minimumTotal(List<List<Integer>> tri) {
        int n = tri.size();
        int ans = Integer.MAX_VALUE;
        int[] f = new int[n];
        f[0] = tri.get(0).get(0);
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                int val = tri.get(i).get(j);
                if (j == i) f[j] = f[j-1] + val;
                if (j < i && j > 0) f[j] = Math.min(f[j-1], f[j]) + val;
                if (j == 0) f[j] += val;
            }
        }
        for (int i = 0; i < n; i++) ans = Math.min(ans, f[i]);
        return ans;
    }
}
