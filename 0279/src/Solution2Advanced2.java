import java.util.Arrays;

/**
 * @author YonghShan
 * @date 2/25/21 - 00:02
 */
class Solution2Advanced2 {
    // 「完全背包」+「一维空间优化」+状态转移时的内外层维度交换（相当于按纵列填充dp数组，而不是常规的按横行填充）
    // 对应 LeetCode Approach #2
    // DP: numSquares(n) = min(numSquares(n-k)) + 1, ∀ k ∈ {square numbers}
    // e.g. n = 13时， k ∈ {1, 4, 9} => numSquares(13) = min(numSquares(13-1)，numSquares(13-4)，numSquares(13-9)) + 1
    //                                                = min(numSquares(12)，numSquares(9)，numSquares(4)) + 1
    // 因为Solution1对于中间答案的重复计算，用dp数组存储中间答案
    /* Runtime: 30ms (faster than 78.48%)      O(n*sqrt(n))
       Memory: 39.8MB (less than 27.58%)       O(n)
     */
    public int numSquares(int n) {
        int[] dp = new int[n+1];

        for (int c = 1; c < n+1; c++) {      // O(n)
            int iLen = (int) Math.sqrt(c);
            int min = n; // 每计算一个新的c的dp，要重新置min，不然min还是上一个数的min，导致dp[]更新不了
            for (int i = 0; i < iLen; i++) {   // O(sqrt(n))
                int t = (i+1)*(i+1);
                min = Math.min(min, dp[c-t]);
            }
            dp[c] = min+1;
        }

        return dp[n];
    }
}
