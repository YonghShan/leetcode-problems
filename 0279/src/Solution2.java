/**
 * @author YonghShan
 * @date 8/30/21 - 14:14
 */
public class Solution2 {
    // 「完全背包」
    // DP: numSquares(n) = min(numSquares(n-k)) + 1, ∀ k ∈ {square numbers}
    // e.g. n = 13时， k ∈ {1, 4, 9} => numSquares(13) = min(numSquares(13-1)，numSquares(13-4)，numSquares(13-9)) + 1
    //                                                = min(numSquares(12)，numSquares(9)，numSquares(4)) + 1
    // 因为Solution1对于中间答案的重复计算，用dp数组存储中间答案
    /* Runtime: 1097ms    O(n^2*sqrt(n))
       Memory: 47MB     O(n*sqrt(n))
     */
    public int numSquares(int n) {
        // 预处理出所有可能用到的「完全平方数」
        int len = (int) Math.sqrt(n); // 转为int是为了向下取整     O(sqrt(n))
        int[] perfectSquares = new int[len];
        for (int i = 0; i < len; i++) perfectSquares[i] = (i + 1) * (i + 1);

        // f[i][c] 代表考虑前 i 个物品，凑出 c 所使用到的最小元素个数
        int[][] f = new int[len][n + 1];

        // 处理「第一个完全平方数」的情况
        for (int c = 0; c <= n; c++) f[0][c] = c;

        // 处理「剩余数」的情况
        for (int i = 1; i < len; i++) { // O(sqrt(n))
            int t = perfectSquares[i];
            for (int c = 0; c <= n; c++) { // O(n)
                // 「不选」：
                f[i][c] = f[i-1][c];
                // 「选」：
                for (int k = 1; k * t <= c; k++)  // O(n)
                    f[i][c] = Math.min(f[i][c], f[i-1][c- k*t]+k);
            }
        }

        return f[len-1][n];
    }
}
