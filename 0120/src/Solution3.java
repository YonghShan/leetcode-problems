import java.util.List;

/**
 * @author YonghShan
 * @date 7/31/21 - 01:51
 */
public class Solution3 {
    // 推荐！
    // i 倒序 改用一维数组
    /* Runtime: 1ms
       Memory: 39.3MB   O(n)
     */
    public int minimumTotal(List<List<Integer>> tri) {
        int n = tri.size();
        int[] f = new int[n];
        for (int i = 0; i < n; i++) f[i] = tri.get(tri.size()-1).get(i); // 即f初始化为tri的最后一行
        for (int i = n-2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int val = tri.get(i).get(j);
                f[j] = Math.min(f[j], f[j+1]) + val;
            }
        }
        return f[0];
    }
}
