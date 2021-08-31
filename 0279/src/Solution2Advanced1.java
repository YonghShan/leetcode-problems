/**
 * @author YonghShan
 * @date 8/30/21 - 14:15
 */
public class Solution2Advanced1 {
    // 「完全背包」+「一维空间优化」
    // DP: numSquares(n) = min(numSquares(n-k)) + 1, ∀ k ∈ {square numbers}
    // e.g. n = 13时， k ∈ {1, 4, 9} => numSquares(13) = min(numSquares(13-1)，numSquares(13-4)，numSquares(13-9)) + 1
    //                                                = min(numSquares(12)，numSquares(9)，numSquares(4)) + 1
    // 因为Solution1对于中间答案的重复计算，用dp数组存储中间答案
    /* Runtime: 40ms      O(n*sqrt(n)): O(n)的for loop里嵌套O(sqrt(n))的for loop
       Memory: 40.1MB     O(n)
     */
    public int numSquares(int n) {
        // 不预处理出所有可能用到的「完全平方数」，而是之后再利用i枚举
        int len = (int) Math.sqrt(n); // 转为int是为了向下取整     O(sqrt(n))

        // f[i][c] 代表考虑前 i 个物品，凑出 c 所使用到的最小元素个数
        int[] f = new int[n + 1];

        // 处理「第一个完全平方数」的情况
        for (int c = 0; c <= n; c++) f[c] = c;

        // 处理「剩余数」的情况
        for (int i = 1; i < len; i++) { // O(sqrt(n))
            int t = (i+1)*(i+1);
            // 「选」的前提是c>=t，而「不选」时不需要更改f数组，故c从t开始  O(n)
            for (int c = t; c <= n; c++) f[c] = Math.min(f[c], f[c-t]+1);
        }

        return f[n];
    }
}
