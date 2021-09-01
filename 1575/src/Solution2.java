/**
 * @author YonghShan
 * @date 8/8/21 - 11:10
 */
public class Solution2 {
    // 将memoization改为DP
    /* Runtime: 228ms (faster than 25.41%)   O(n^2 * fuel)
       Memory: 41.2MB (less than 14.75%)     O(n^2 * fuel)
     */
    int mod = 1000000007;
    public int countRoutes(int[] ls, int start, int end, int fuel) {
        int n = ls.length;

        // f[i][j] 代表从位置 i 出发，当前油量为 j 时，到达目的地的路径数
        int[][] f = new int[n][fuel + 1];

        // 对于本身位置就在目的地的状态，路径数为 1
        for (int i = 0; i <= fuel; i++) f[end][i] = 1;

        // 从状态转移方程可以发现 f[i][fuel]=f[i][fuel]+f[k][fuel-need]
        // 在计算 f[i][fuel] 的时候依赖于 f[k][fuel-need]
        // 其中 i 和 k 并无严格的大小关系
        // 而 fuel 和 fuel-need 具有严格大小关系：fuel >= fuel-need
        // 因此外层循环fuel（从小到大），内层循环城市（无所谓顺序），内外层顺序不可变
        for (int cur = 0; cur <= fuel; cur++) {
            for (int i = 0; i < n; i++) {
                for (int k = 0; k < n; k++) {
                    if (i != k) {
                        int need = Math.abs(ls[i] - ls[k]);
                        if (cur >= need) {
                            f[i][cur] += f[k][cur-need];
                            f[i][cur] %= mod;
                        }
                    }
                }
            }
        }
        return f[start][fuel];
    }
}
